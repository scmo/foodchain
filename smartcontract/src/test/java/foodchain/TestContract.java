
package foodchain;

import org.junit.Assert;
import org.junit.Test;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.crypto.CipherException;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class TestContract {
    @Test
    public void testVersion() throws ExecutionException, InterruptedException {
        Web3j web3 = Web3j.build(new HttpService());  // defaults to http://localhost:8545/
        Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().sendAsync().get();
        String clientVersion = web3ClientVersion.getWeb3ClientVersion();
        Assert.assertEquals("Geth/v1.5.7-stable/linux/go1.7.4", clientVersion);
    }

    @Test
    public void testDeploy() throws Exception {
        SmartContract sm = new SmartContract();
        String adr = sm.deploy("farmX");
        System.out.println(adr);
        sm.updateWithAddress(adr, "cow1", "farm1" ,1, 2 , 3);
        Cow cow = sm.cowWithAddress(adr, "cow1");
        Assert.assertEquals("farm1", cow.farmAddress());
        Assert.assertEquals(0, cow.status());
        Assert.assertEquals(1, cow.stepCounter());
        Assert.assertEquals(2, cow.outsideTimeInSeconds());
        Assert.assertEquals(3, cow.insideTimeInSeconds());

    }

    @Test
    public void testDeployName() throws Exception {
        SmartContract sm = new SmartContract();
        String adr = sm.deploy("farmX");
        System.out.println(adr);
        Address a = sm.lookupName("farmX");
        System.out.println("Add: "+a);
        sm.update("farmX", "cow1", "farm1" ,1, 2 , 3);
        Cow cow = sm.cow("farmX", "cow1");
        Assert.assertEquals("farm1", cow.farmAddress());
        Assert.assertEquals(0, cow.status());
        Assert.assertEquals(1, cow.stepCounter());
        Assert.assertEquals(2, cow.outsideTimeInSeconds());
        Assert.assertEquals(3, cow.insideTimeInSeconds());

    }

    @Test
    public void testConvert() {
        String test = "cow1";
        byte[] tmp = new byte[32];
        System.arraycopy(test.getBytes(), 0, tmp, 0, test.getBytes().length);
        Bytes32 b = new Bytes32(tmp);
        System.out.println(new String(b.getValue()).replaceAll("\0", ""));
    }

    @Test
    public void testLookup() throws Exception {
        SmartContract sm = new SmartContract();
        Address a = sm.lookupName("farmX");
        System.out.println(a);
    }
}