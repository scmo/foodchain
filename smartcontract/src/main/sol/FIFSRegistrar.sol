/*
 * Copyright 2017 Thomas Bocek
 *
 * Licensed under the Apache License, Version 2.0 (the 'License'); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an 'AS IS' BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

pragma solidity ^0.4.4;


/**
 * A registrar that allocates subdomains to the first person to claim them.
 */
contract FIFSRegistrar {
    AbstractENS ens;
    bytes32 rootNode;

    modifier only_owner(bytes32 subnode) {
        var node = sha3(rootNode, subnode);
        var currentOwner = ens.owner(node);
        if(currentOwner != 0 && currentOwner != msg.sender)
            throw;
        _;
    }

    /**
     * Constructor.
     * @param ensAddr The address of the ENS registry.
     * @param node The node that this registrar administers.
     */
    function FIFSRegistrar(address ensAddr, bytes32 node) {
        ens = AbstractENS(ensAddr);
        rootNode = node;
    }

    /**
     * Register a name, or change the owner of an existing registration.
     * @param subnode The hash of the label to register.
     * @param owner The address of the new owner.
     */
    function register(bytes32 subnode, address owner) only_owner(subnode) {
        ens.setSubnodeOwner(rootNode, subnode, this);
        ens.setResolver(sha3(rootNode, subnode), owner);
    }

    function lookup(string name) constant returns (address) {
        var node = sha3(rootNode, sha3(name));
        var resolver = Resolver(ens.resolver(node));
        var hash = resolver.addr(node);
        return hash;
    }
}
contract AbstractENS {
    function owner(bytes32 node) constant returns(address);
    function resolver(bytes32 node) constant returns(address);
    function ttl(bytes32 node) constant returns(uint64);
    function setOwner(bytes32 node, address owner);
    function setSubnodeOwner(bytes32 node, bytes32 label, address owner);
    function setResolver(bytes32 node, address resolver);
    function setTTL(bytes32 node, uint64 ttl);
}

contract Resolver {
    function addr(bytes32) returns (address);
}