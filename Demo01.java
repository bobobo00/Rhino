

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
 * ���Խű�����ִ��javaSrcript����
 * @author dell
 *
 */
public class Demo01 {
	public static void main(String[] args) throws ScriptException, NoSuchMethodException, IOException {
		//��ýű��������
		ScriptEngineManager sem=new ScriptEngineManager();
		ScriptEngine engine=sem.getEngineByName("javascript");
		
		//�������,��洢������������С�
		engine.put("msg","001");
		String  str="var user= {name:'hi',age:18,school:['�廪��ѧ','������ѧ']};";
		str+="print(user.name);";
		
		//ִ�нű����ȿ��Ա�java����Ҳ���Ա��ű�������
		engine.eval(str);
		engine.eval("msg='hh';");//�ű�����
		System.out.println(engine.get("msg"));//java�鿴
		
		
		System.out.println("**********************");
		//���庯��
		engine.eval("function add(a,b){var sum=a+b;return a+b;}");
		//ִ�е��ýӿ�
		Invocable jsInvoke=(Invocable)engine;
		//ִ�нű��ж��巽��
		Object result=jsInvoke.invokeFunction("add", new Object[] {13,20});
		System.out.println(result);
		
		//��������java���������������е�java��
		String jsCode="import Package(java.util*); var list=Arrays.asList([\"hh\",\"ii\"]);";
		engine.eval(jsCode);
		
		List<String> ll=(List<String>)engine.get("list");
		for(String s:ll) {
			System.out.println(s);
		}
		
		//ִ��һ��js�ļ�������a.js������Ŀ��src�£�
		URL url=Demo01.class.getClassLoader().getResource("a.js");
		FileReader reader=new FileReader(url.getPath());
		engine.eval(reader);
		reader.close();
		
	}

}
