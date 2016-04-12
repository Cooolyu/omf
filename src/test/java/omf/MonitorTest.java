package omf;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.gmsz.om.common.utils.PostStreamUtil;

public class MonitorTest {
	public static void main(String[] args)throws Exception {
		//String addTime=TimeUtil.parseDate2String(new Date(), "yyyy/MM/dd HH:mm:ss");
		//String url="http://192.168.18.248:8081/omd/do/information/status/push";
		String url="/information/status/push";
		String stream="{\"SerialNumber\":\"no_0001\",\"Os\":\"RuiJie Router\",\"Status\":1,\"Port\":[{\"Value\":\"10\",\"Item\":\"端口3上行使用率（%）\"},{\"Value\":\"1\",\"Item\":\"端口2上行使用率（%）\"},{\"Value\":\"3\",\"Item\":\"端口3下行使用率（%）\"},{\"Value\":\"10\",\"Item\":\"端口2下行使用率（%）\"}],\"Memory\":[{\"Value\":\"75\",\"Item\":\"内存使用率（%）\"}],\"Cpu\":[{\"Value\":\"1\",\"Item\":\"CPU使用率（%）\"},{\"Value\":\"1\",\"Item\":\"CPU2使用率（%）\"},{\"Value\":\"1\",\"Item\":\"CPU1使用率（%）\"}],\"MonitorPointId\":50,\"AddTime\":\"2016/01/26 16:58:00\"}";
		JSONObject obj=(JSONObject) JSONValue.parse(stream);
		JSONObject jsonObj = PostStreamUtil.postStreamToOmd(url,obj );
		System.out.println(jsonObj.toJSONString());
	}
	
	
	

}
