

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * 测试脚本引擎执行javaSrcript代码
 * @author dell
 *
 */
public class Demo01 {
	public static void main(String[] args) throws ScriptException, NoSuchMethodException, IOException {
		//获得脚本引擎对象
		ScriptEngineManager sem=new ScriptEngineManager();
		ScriptEngine engine=sem.getEngineByName("javascript");
		
		//定义变量,会存储到引擎的上文中。
		engine.put("msg","001");
		String  str="var user= {name:'hi',age:18,school:['清华大学','北京大学']};";
		str+="print(user.name);";
		
		//执行脚本，既可以被java操作也可以被脚本操作。
		engine.eval(str);
		engine.eval("msg='hh';");//脚本操作
		System.out.println(engine.get("msg"));//java查看
		
		
		System.out.println("**********************");
		//定义函数
		engine.eval("function add(a,b){var sum=a+b;return a+b;}");
		//执行调用接口
		Invocable jsInvoke=(Invocable)engine;
		//执行脚本中定义方法
		Object result=jsInvoke.invokeFunction("add", new Object[] {13,20});
		System.out.println(result);
		
		//导入其他java包，调用其他包中的java类
		String jsCode="import Package(java.util*); var list=Arrays.asList([\"hh\",\"ii\"]);";
		engine.eval(jsCode);
		
		List<String> ll=(List<String>)engine.get("list");
		for(String s:ll) {
			System.out.println(s);
		}
		
		//执行一个js文件，（将a.js置于项目的src下）
		URL url=Demo01.class.getClassLoader().getResource("a.js");
		FileReader reader=new FileReader(url.getPath());
		engine.eval(reader);
		reader.close();
		
	}

}
