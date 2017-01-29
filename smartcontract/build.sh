#!/bin/bash

solc src/main/sol/FoodChain.sol --bin --abi --optimize -o src/main/sol/
gradle  -PmainClass=foodchain.CLI1 execute

solc src/main/sol/FIFSRegistrar.sol --bin --abi --optimize -o src/main/sol/
gradle  -PmainClass=foodchain.CLI2 execute

#https://dl.dropboxusercontent.com/u/4270001/testnet_genesis.json
#geth --testnet attach http://127.0.0.1:8545
#geth --testnet --rpc --rpcapi personal,db,eth,net,web3

#loadScript

#personal.unlockAccount(eth.accounts[0], "123456", 999999);
#loaddScript("/home/draft/git/foodchain/ensutils.js");
