package foodchain;

import java.lang.String;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import org.web3j.abi.Contract;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint32;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

/**
 * <p>Auto generated code.<br>
 * <strong>Do not modify!</strong><br>
 * Please use {@link org.web3j.codegen.SolidityFunctionWrapperGenerator} to update.</p>
 */
public final class FoodChain extends Contract {
    private static final String BINARY = "606060405234610000575b60018054600160a060020a033316600160a060020a031991821681179092556002805490911690911790555b5b610833806100466000396000f300606060405236156100885763ffffffff60e060020a60003504166329947a13811461008d5780632e655730146100f8578063645af6d914610140578063714ca971146101ab57806381b0c512146101f35780639912bc0814610218578063ae8421e114610242578063b1ca8dd614610251578063cfefb3d51461027f578063e5b4c966146102a3575b610000565b346100005761009d6004356102bc565b60408051602080825283518183015283519192839290830191858101910280838382156100e5575b8051825260208311156100e557601f1990920191602091820191016100c5565b5050509050019250505060405180910390f35b346100005761010860043561032b565b6040805163ffffffff968716815294861660208601529285168484015293166060830152608082019290925290519081900360a00190f35b346100005761009d600435610373565b60408051602080825283518183015283519192839290830191858101910280838382156100e5575b8051825260208311156100e557601f1990920191602091820191016100c5565b5050509050019250505060405180910390f35b34610000576101086004356103eb565b6040805163ffffffff968716815294861660208601529285168484015293166060830152608082019290925290519081900360a00190f35b610216600160a060020a036004351660243563ffffffff6044351660643561043a565b005b346100005761021660043560243563ffffffff60443581169060643581169060843516610555565b005b34610000576102166105db565b005b346100005761026d600435600160a060020a0360243516610601565b60408051918252519081900360200190f35b346100005761028f60043561062c565b604080519115158252519081900360200190f35b610216600160a060020a03600435166024356106b0565b005b604080516020818101835260008083528481528082528390206005018054845181840281018401909552808552929392909183018282801561031e57602002820191906000526020600020905b81548152600190910190602001808311610309575b505050505090505b919050565b6000602081905290815260409020805460019091015463ffffffff808316926401000000008104821692680100000000000000008204831692606060020a9092049091169085565b604080516020818101835260008083528481528082528390206002018054845181840281018401909552808552929392909183018282801561031e57602002820191906000526020600020905b8154600160a060020a031681526001909101906020018083116103c0575b505050505090505b919050565b6000818152602081905260409020805460019091015463ffffffff808316926401000000008104821692680100000000000000008204831692606060020a909204909116905b91939590929450565b600254600160a060020a038581169116141561054d576002805473ffffffffffffffffffffffffffffffffffffffff191633600160a060020a0390811691909117909155600084815260208181526040808320938816835260038401825280832080543490810190915560049094019091529020805490910190556104bf83856106fe565b600083815260208190526040902080546fffffffff0000000000000000000000001916606060020a63ffffffff8516021781556005018054600181018083558281838015829011610535576000838152602090206105359181019083015b80821115610531576000815560010161051d565b5090565b5b505050916000526020600020900160005b50829055505b5b5b50505050565b60015433600160a060020a03908116911614156105d35760008581526020819052604090206001810185905580546bffffffff000000000000000019166801000000000000000063ffffffff868116919091029190911763ffffffff19168482161767ffffffff000000001916640100000000918416919091021790555b5b5050505050565b60025433600160a060020a03908116911614156105fe5733600160a060020a0316ff5b5b565b600082815260208181526040808320600160a060020a03851684526003019091529020545b92915050565b600081815260208181526040808320600160a060020a03331684526004019091528120548190156106a5575060008281526020818152604080832033600160a060020a03168085526004909101909252808320805490849055905190926108fc841502918491818181858888f1935050505091506106aa565b600091505b50919050565b600081815260208181526040808320600160a060020a03861684526003810183528184208054349081019091556004909101909252909120805490910190556106f981836106fe565b5b5050565b60005b60008381526020819052604090206002015481101561077f5760008381526020819052604090206002018054600160a060020a0384169190839081101561000057906000526020600020900160005b9054906101000a9004600160a060020a0316600160a060020a0316141561077657610802565b5b600101610701565b600083815260208190526040902060020180546001810180835582818380158290116107d0576000838152602090206107d09181019083015b80821115610531576000815560010161051d565b5090565b5b505050916000526020600020900160005b8154600160a060020a038087166101009390930a92830292021916179055505b5050505600a165627a7a72305820665aec6b7cea699f421c40b004cef29a8b1577baeaf1223d2e45b253fe373f6e0029";

    private FoodChain(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public Future<DynamicArray<Bytes32>> cowData(Bytes32 cowId) {
        Function function = new Function("cowData", 
                Arrays.<Type>asList(cowId), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Bytes32>>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<List<Type>> cows(Bytes32 param0) {
        Function function = new Function("cows", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint32>() {}, new TypeReference<Uint32>() {}, new TypeReference<Uint32>() {}, new TypeReference<Uint32>() {}, new TypeReference<Bytes32>() {}));
        return executeCallMultipleValueReturnAsync(function);
    }

    public Future<DynamicArray<Address>> cowPaymentTo(Bytes32 cowId) {
        Function function = new Function("cowPaymentTo", 
                Arrays.<Type>asList(cowId), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<List<Type>> cow(Bytes32 cowId) {
        Function function = new Function("cow", 
                Arrays.<Type>asList(cowId), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint32>() {}, new TypeReference<Uint32>() {}, new TypeReference<Uint32>() {}, new TypeReference<Uint32>() {}, new TypeReference<Bytes32>() {}));
        return executeCallMultipleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> payTo(Address payTo, Bytes32 cowId, Uint32 status, Bytes32 data) {
        Function function = new Function("payTo", Arrays.<Type>asList(payTo, cowId, status, data), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> update(Bytes32 cowId, Bytes32 farmAddress, Uint32 stepCounter, Uint32 outsideTimeInSeconds, Uint32 insideTimeInSeconds) {
        Function function = new Function("update", Arrays.<Type>asList(cowId, farmAddress, stepCounter, outsideTimeInSeconds, insideTimeInSeconds), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> done() {
        Function function = new Function("done", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Uint256> cowPaymentAmount(Bytes32 cowId, Address payTo) {
        Function function = new Function("cowPaymentAmount", 
                Arrays.<Type>asList(cowId, payTo), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> payout(Bytes32 cowId) {
        Function function = new Function("payout", Arrays.<Type>asList(cowId), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> directPay(Address payTo, Bytes32 cowId) {
        Function function = new Function("directPay", Arrays.<Type>asList(payTo, cowId), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public static Future<FoodChain> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialValue) {
        return deployAsync(FoodChain.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialValue);
    }

    public static FoodChain load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new FoodChain(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }


}
