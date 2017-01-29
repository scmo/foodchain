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


 contract FoodChain {
    //the data is aggregated on a daily basis

    //var node = namehash("foodchain.test")
    //bytes32 constant ensRoot =  0x9e07b8794cc8e118f2e0dd2c168f3b38d4e21491a3bbef6557b7fb94976fc770;
    //contract address of resolver

    FIFSRegistrar constant registrar = FIFSRegistrar(0x2bc4c198325853db29293b6a62f36a62f7cbcf45);

    //address af public ENS
    //AbstractENS ens = AbstractENS(0x112234455c3a32fd11230c42e7bccd4a84e02010);


    //ens.setOwner(namehash("foodchain.test"), fifsregistrar.address, {from:eth.accounts[0]});
    //loadScript("/home/draft/git/foodchain/ensutils.js");

    struct Cow
    {
        uint32 outsideTimeInSeconds;
        uint32 insideTimeInSeconds;
        uint32 stepCounter;
        uint32 status; //0 is on farm, 1 is in transit, 2 is slaugtherhouse, 3 is butcher
        bytes32 farmAddress; // farmaddress, max 32 bytes
        address[] keys;
        mapping (address => uint256) balanceTotal;
        mapping (address => uint256) balanceCurrent;
        bytes32[] data;
    }

    //kvd as a hash
    mapping(bytes32 => Cow) public cows;

    address initialOwner;
    address currentOwner;
    function FoodChain(string name) {
        initialOwner = msg.sender;
        currentOwner = msg.sender;
        registrar.register(sha3(name), this);
    }

    /* Any remaining funds should be sent back to the sender */
    function done() {
        if (msg.sender == currentOwner) {
            suicide(msg.sender);
        }
    }

    function payTo(address payTo, bytes32 cowId, uint32 status, bytes32 data) payable {
        if(currentOwner == payTo) {
            currentOwner = msg.sender;
            cows[cowId].balanceTotal[payTo] += msg.value;
            cows[cowId].balanceCurrent[payTo] += msg.value;
            insertKey(cowId, payTo);
            cows[cowId].status = status;
            if(data.length > 0) {
                cows[cowId].data.push(data);
            }
        }
    }

    function directPay(address payTo, bytes32 cowId) payable {
        cows[cowId].balanceTotal[payTo] += msg.value;
        cows[cowId].balanceCurrent[payTo] += msg.value;
        insertKey(cowId, payTo);
    }

    function insertKey(bytes32 cowId, address key) internal {
        for (uint i = 0; i < cows[cowId].keys.length; i++) {
            if(cows[cowId].keys[i] == key) {
                return;
            }
        }
        cows[cowId].keys.push(key);
    }

    function update(bytes32 cowId, bytes32 farmAddress, uint32 stepCounter,
            uint32 outsideTimeInSeconds, uint32 insideTimeInSeconds) {
        if (msg.sender == initialOwner) {
            cows[cowId].farmAddress = farmAddress;
            cows[cowId].stepCounter += stepCounter;
            cows[cowId].outsideTimeInSeconds += outsideTimeInSeconds;
            cows[cowId].insideTimeInSeconds += insideTimeInSeconds;
        }
    }

    function payout(bytes32 cowId) returns (bool) {
        if(cows[cowId].balanceCurrent[msg.sender] != 0 ) {
            uint256 amountToSend = cows[cowId].balanceCurrent[msg.sender];
            cows[cowId].balanceCurrent[msg.sender] = 0;
            return msg.sender.send(amountToSend);
        }
        return false;
    }

    function cow(bytes32 cowId) constant returns (uint32, uint32, uint32, uint32, bytes32) {
        return (cows[cowId].outsideTimeInSeconds,
            cows[cowId].insideTimeInSeconds,
            cows[cowId].stepCounter,
            cows[cowId].status,
            cows[cowId].farmAddress);
    }

    function cowData(bytes32 cowId) constant returns (bytes32[]) {
        return cows[cowId].data;
    }

    function cowPaymentTo(bytes32 cowId) constant returns (address[]) {
        return cows[cowId].keys;
    }

    function cowPaymentAmount(bytes32 cowId, address payTo) constant returns (uint256) {
        return cows[cowId].balanceTotal[payTo];
    }

    function addr(bytes32 node) constant returns (address) {
      return this;
    }
}

contract FIFSRegistrar {
    function register(bytes32 subnode, address owner);
    function lookup(string name) constant returns (address);
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