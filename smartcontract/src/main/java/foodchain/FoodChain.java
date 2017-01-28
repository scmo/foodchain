package foodchain;

import java.lang.String;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import org.web3j.abi.Contract;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
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
    private static final String BINARY = "606060405234610000576040516109af3803806109af833981016040528051015b60018054600160a060020a033316600160a060020a031991821681179092556002805490911690911790556040518151735aa32dc46e33b9de99271d03b27d8a44baeb5e539163d22057a991849190819060208401908083835b602083106100995780518252601f19909201916020918201910161007a565b5181516020939093036101000a60001901801990911692169190911790526040805191909301819003812063ffffffff87167c0100000000000000000000000000000000000000000000000000000000028252600482015230600160a060020a03166024820152915160448084019550600094509092839003019050818387803b156100005760325a03f115610000575050505b505b6108718061013e6000396000f300606060405236156100935763ffffffff60e060020a60003504166329947a1381146100985780632e655730146101035780633b3b57de1461014b578063645af6d914610177578063714ca971146101e257806381b0c5121461022a5780639912bc081461024f578063ae8421e114610279578063b1ca8dd614610288578063cfefb3d5146102b6578063e5b4c966146102da575b610000565b34610000576100a86004356102f3565b60408051602080825283518183015283519192839290830191858101910280838382156100f0575b8051825260208311156100f057601f1990920191602091820191016100d0565b5050509050019250505060405180910390f35b3461000057610113600435610362565b6040805163ffffffff968716815294861660208601529285168484015293166060830152608082019290925290519081900360a00190f35b346100005761015b6004356103aa565b60408051600160a060020a039092168252519081900360200190f35b34610000576100a86004356103b1565b60408051602080825283518183015283519192839290830191858101910280838382156100f0575b8051825260208311156100f057601f1990920191602091820191016100d0565b5050509050019250505060405180910390f35b3461000057610113600435610429565b6040805163ffffffff968716815294861660208601529285168484015293166060830152608082019290925290519081900360a00190f35b61024d600160a060020a036004351660243563ffffffff60443516606435610478565b005b346100005761024d60043560243563ffffffff60443581169060643581169060843516610593565b005b346100005761024d610619565b005b34610000576102a4600435600160a060020a036024351661063f565b60408051918252519081900360200190f35b34610000576102c660043561066a565b604080519115158252519081900360200190f35b61024d600160a060020a03600435166024356106ee565b005b604080516020818101835260008083528481528082528390206005018054845181840281018401909552808552929392909183018282801561035557602002820191906000526020600020905b81548152600190910190602001808311610340575b505050505090505b919050565b6000602081905290815260409020805460019091015463ffffffff808316926401000000008104821692680100000000000000008204831692606060020a9092049091169085565b305b919050565b604080516020818101835260008083528481528082528390206002018054845181840281018401909552808552929392909183018282801561035557602002820191906000526020600020905b8154600160a060020a031681526001909101906020018083116103fe575b505050505090505b919050565b6000818152602081905260409020805460019091015463ffffffff808316926401000000008104821692680100000000000000008204831692606060020a909204909116905b91939590929450565b600254600160a060020a038581169116141561058b576002805473ffffffffffffffffffffffffffffffffffffffff191633600160a060020a0390811691909117909155600084815260208181526040808320938816835260038401825280832080543490810190915560049094019091529020805490910190556104fd838561073c565b600083815260208190526040902080546fffffffff0000000000000000000000001916606060020a63ffffffff8516021781556005018054600181018083558281838015829011610573576000838152602090206105739181019083015b8082111561056f576000815560010161055b565b5090565b5b505050916000526020600020900160005b50829055505b5b5b50505050565b60015433600160a060020a03908116911614156106115760008581526020819052604090206001810185905580546bffffffff000000000000000019166801000000000000000063ffffffff868116919091029190911763ffffffff19168482161767ffffffff000000001916640100000000918416919091021790555b5b5050505050565b60025433600160a060020a039081169116141561063c5733600160a060020a0316ff5b5b565b600082815260208181526040808320600160a060020a03851684526003019091529020545b92915050565b600081815260208181526040808320600160a060020a03331684526004019091528120548190156106e3575060008281526020818152604080832033600160a060020a03168085526004909101909252808320805490849055905190926108fc841502918491818181858888f1935050505091506106e8565b600091505b50919050565b600081815260208181526040808320600160a060020a0386168452600381018352818420805434908101909155600490910190925290912080549091019055610737818361073c565b5b5050565b60005b6000838152602081905260409020600201548110156107bd5760008381526020819052604090206002018054600160a060020a0384169190839081101561000057906000526020600020900160005b9054906101000a9004600160a060020a0316600160a060020a031614156107b457610840565b5b60010161073f565b6000838152602081905260409020600201805460018101808355828183801582901161080e5760008381526020902061080e9181019083015b8082111561056f576000815560010161055b565b5090565b5b505050916000526020600020900160005b8154600160a060020a038087166101009390930a92830292021916179055505b5050505600a165627a7a723058203ec4cf9f1690aaa62a9931df0ce488555569a6a4c831bea7d3921c0d1c2afb340029";

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

    public Future<TransactionReceipt> addr(Bytes32 node) {
        Function function = new Function("addr", Arrays.<Type>asList(node), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
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

    public static Future<FoodChain> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialValue, Utf8String name) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(name));
        return deployAsync(FoodChain.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor, initialValue);
    }

    public static FoodChain load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new FoodChain(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }
}
