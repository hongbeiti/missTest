package cn.com.webxml;

import java.util.List;

import org.junit.Test;

public class AppMain {

	@Test
	public void test() {

		IpAddressSearchWebService ipasws = new IpAddressSearchWebService();

		IpAddressSearchWebServiceSoap ipwss = ipasws.getIpAddressSearchWebServiceSoap();

		ArrayOfString aos = ipwss.getCountryCityByIp("222.22.22.222");

		List<String> strs = aos.getString();

		for (String str : strs) {
			System.out.println(str);
		}
	}
}

