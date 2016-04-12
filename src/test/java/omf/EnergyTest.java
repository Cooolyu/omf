package omf;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.gmsz.om.common.utils.PostStreamUtil;

public class EnergyTest {
	public static void main(String[] args)throws Exception {
		String url="/energy/data/push";
		String stream="{'MonitorPointId':50 ,'EnergyType':1,'EneryValue': [{'Item':'电子系统','Value':'353'},{'Item':' 空调系统','Value':'394'},{'Item':' 服务器','Value':'450'}],'Unit':'Kwh','CountDate':'2015/9/23','AddTime':'2015/9/23 13:04:09'}";
		JSONObject obj=(JSONObject) JSONValue.parse(stream);
		JSONObject jsonObj = PostStreamUtil.postStreamToOmd(url,obj );
		System.out.println(jsonObj.toJSONString());
	}
	
	
	

}
