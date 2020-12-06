package org.eclipse.jst.j2ee.internal.web.operations;

import java.util.*;
import org.eclipse.jst.j2ee.internal.common.operations.*;

public class ServletTemplate
{
  protected static String nl;
  public static synchronized ServletTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ServletTemplate result = new ServletTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "package ";
  protected final String TEXT_2 = ";";
  protected final String TEXT_3 = NL;
  protected final String TEXT_4 = NL + "import ";
  protected final String TEXT_5 = NL + "@WebServlet";
  protected final String TEXT_6 = "(\"";
  protected final String TEXT_7 = "\")";
  protected final String TEXT_8 = "({ ";
  protected final String TEXT_9 = ", ";
  protected final String TEXT_10 = "\"";
  protected final String TEXT_11 = " })";
  protected final String TEXT_12 = "(";
  protected final String TEXT_13 = NL + "\t\t";
  protected final String TEXT_14 = " = \"";
  protected final String TEXT_15 = " = { ";
  protected final String TEXT_16 = NL + "\t\t\t\t";
  protected final String TEXT_17 = " ";
  protected final String TEXT_18 = "}";
  protected final String TEXT_19 = NL + "\t\t\t\t@WebInitParam(name = \"";
  protected final String TEXT_20 = "\", value = \"";
  protected final String TEXT_21 = ", description = \"";
  protected final String TEXT_22 = ")";
  protected final String TEXT_23 = NL + "\t\t}";
  protected final String TEXT_24 = " = ";
  protected final String TEXT_25 = NL + "public ";
  protected final String TEXT_26 = "abstract ";
  protected final String TEXT_27 = "final ";
  protected final String TEXT_28 = "class ";
  protected final String TEXT_29 = " extends ";
  protected final String TEXT_30 = " implements ";
  protected final String TEXT_31 = " {";
  protected final String TEXT_32 = NL + "\tprivate static final long serialVersionUID = 1L;";
  protected final String TEXT_33 = NL + NL + "    public ";
  protected final String TEXT_34 = "() {" + NL + "    }";
  protected final String TEXT_35 = NL + "       " + NL + "    public ";
  protected final String TEXT_36 = ") {" + NL + "        super(";
  protected final String TEXT_37 = ");" + NL + "    }";
  protected final String TEXT_38 = ") ";
  protected final String TEXT_39 = "throws ";
  protected final String TEXT_40 = " { ";
  protected final String TEXT_41 = NL + "         // TODO Auto-generated method stub";
  protected final String TEXT_42 = NL + "\t\t\treturn ";
  protected final String TEXT_43 = NL + "    }";
  protected final String TEXT_44 = NL + "\t@Override" + NL + "\tpublic void init(ServletConfig config) throws ServletException {" + NL + "\t}";
  protected final String TEXT_45 = NL + "\t@Override" + NL + "\tpublic void destroy() {" + NL + "\t}";
  protected final String TEXT_46 = NL + "\t@Override" + NL + "\tpublic ServletConfig getServletConfig() {" + NL + "\t\treturn null;" + NL + "\t}";
  protected final String TEXT_47 = NL + "\t@Override" + NL + "\tpublic String getServletInfo() {" + NL + "\t\treturn null; " + NL + "\t}";
  protected final String TEXT_48 = NL + "\t@Override" + NL + "\tpublic void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {" + NL + "\t}";
  protected final String TEXT_49 = NL + "\t@Override" + NL + "\tprotected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {" + NL + "\t}";
  protected final String TEXT_50 = NL + "\t@Override" + NL + "\tprotected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {" + NL + "\t\t// 第\t一步，接收参数" + NL + "\t\tString id = request.getParameter(\"\");" + NL + "\t\t// 第二步，处理业务" + NL + "\t\tString s = null;" + NL + "\t\t// 第三步，输出" + NL + "\t\tresponse.setCharacterEncoding(\"utf-8\");" + NL + "\t\tresponse.setContentType(\"text/html\");" + NL + "\t\t// 如果使用lombok的 @Cleanup: 将自动在最后调用 close() 关闭可关闭流" + NL + "\t\tPrintWriter out = response.getWriter();" + NL + "" + NL + "\t\tout.println(s);" + NL + "\t\tout.flush();" + NL + "\t\tout.close();" + NL + "\t}";
  protected final String TEXT_51 = NL + "\t@Override" + NL + "\tprotected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {";
  protected final String TEXT_52 = NL + "\t\t// 第一步，接收参数，以下可能用到阿里巴巴的 fastjson 组件，必须用到工具类：RequestToBean" + NL + "\t\t//方法一：用 axios 的 POST 提交 JSON 格式的数据，所以使用 fastjson 转换成 javabean 对象" + NL + "//\t\tObject obj = JSON.parseObject(RequestToBean.getRequestPostStr(request), Object.class);" + NL + "\t\t//方法二：用 mui 的 POST 方式提交正常接收" + NL + "//\t\tObject obj = RequestToBean.getBeanToRequest(request, Object.class);" + NL + "\t\t// 第二步，处理业务" + NL + "\t\tString s = null;" + NL + "\t\t// 第三步，输出" + NL + "\t\tresponse.setCharacterEncoding(\"utf-8\");" + NL + "\t\tresponse.setContentType(\"text/html\");" + NL + "\t\t// 如果使用lombok的 @Cleanup: 将自动在最后调用 close() 关闭可关闭流" + NL + "\t\tPrintWriter out = response.getWriter();" + NL + "" + NL + "\t\tout.println(s);" + NL + "\t\tout.flush();" + NL + "\t\tout.close();";
  protected final String TEXT_53 = NL + "\t}";
  protected final String TEXT_54 = NL + "\t@Override" + NL + "\tprotected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {" + NL + "\t}";
  protected final String TEXT_55 = NL + "\t@Override" + NL + "\tprotected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {" + NL + "\t}";
  protected final String TEXT_56 = NL + "\t@Override" + NL + "\tprotected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {" + NL + "\t}";
  protected final String TEXT_57 = NL + "\t@Override" + NL + "\tprotected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {" + NL + "\t}";
  protected final String TEXT_58 = NL + "\t@Override" + NL + "\tprotected void doTrace(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {" + NL + "\t}";
  protected final String TEXT_59 = NL + NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     CreateServletTemplateModel model = (CreateServletTemplateModel) argument; 
    
	model.removeFlags(CreateJavaEEArtifactTemplateModel.FLAG_QUALIFIED_SUPERCLASS_NAME); 

    
	if (model.getJavaPackageName() != null && model.getJavaPackageName().length() > 0) {

    stringBuffer.append(TEXT_1);
    stringBuffer.append( model.getJavaPackageName() );
    stringBuffer.append(TEXT_2);
    
	}

    stringBuffer.append(TEXT_3);
     
	Collection<String> imports = model.getImports();
	for (String anImport : imports) { 

    stringBuffer.append(TEXT_4);
    stringBuffer.append( anImport );
    stringBuffer.append(TEXT_2);
     
	}

    stringBuffer.append(TEXT_3);
     
	if (model.isAnnotated()) { 

     
		if (model.getDescription() != null && model.getDescription().length() > 0) { 

     
		} 
		
		List<String[]> mappings = model.getServletMappings();
 		if (mappings != null && mappings.size() > 0) {
			for (int i = 0; i < mappings.size(); i++) {
				String map = model.getServletMapping(i); 
     
			} 
		}
 		List<String[]> initParams = model.getInitParams();
 		if (initParams != null && initParams.size() > 0) {
    		for (int i = 0; i < initParams.size(); i++) {
				String name = model.getInitParam(i, CreateServletTemplateModel.NAME);
				String value = model.getInitParam(i, CreateServletTemplateModel.VALUE);
 				String description = model.getInitParam(i, CreateServletTemplateModel.DESCRIPTION); 

     
				if (description != null && description.length() > 0) { 

    
				}
			} 
		} 
	} 

     
	if ("3.0".equals(model.getJavaEEVersion()) || "3.1".equals(model.getJavaEEVersion()) || "4.0".equals(model.getJavaEEVersion())) { 

    stringBuffer.append(TEXT_5);
    
		Map<String, Object> params = model.getClassAnnotationParams();
		if (params.size() == 1 && params.containsKey(CreateServletTemplateModel.ATT_URL_PATTERNS)) {
			List<String[]> mappings = (List<String[]>) params.get(CreateServletTemplateModel.ATT_URL_PATTERNS);
			if (mappings.size() == 1) {
				String value = mappings.get(0)[0];

    stringBuffer.append(TEXT_6);
    stringBuffer.append( value );
    stringBuffer.append(TEXT_7);
    
			} else {

    stringBuffer.append(TEXT_8);
    
				boolean needComma = false;
				for (String[] mapping : mappings) {
					if (needComma) {

    stringBuffer.append(TEXT_9);
    
					}

    stringBuffer.append(TEXT_10);
    stringBuffer.append( mapping[0] );
    stringBuffer.append(TEXT_10);
    
					needComma = true;
				}

    stringBuffer.append(TEXT_11);
    
			}
		} else if (!params.isEmpty()) { 

    stringBuffer.append(TEXT_12);
    
			Set<String> keys = params.keySet();
			boolean needNewLine = keys.contains(CreateServletTemplateModel.ATT_INIT_PARAMS) || 
					(keys.contains(CreateServletTemplateModel.ATT_URL_PATTERNS) && 
							((List<String[]>) params.get(CreateServletTemplateModel.ATT_URL_PATTERNS)).size() > 1);
			boolean needComma = false;
			for (String key : keys) {
				if (needComma) {

    stringBuffer.append(TEXT_9);
    
				}
				
				if (needNewLine) {

    stringBuffer.append(TEXT_13);
    
				} 
			
				if (key.equals(CreateServletTemplateModel.ATT_NAME) || key.equals(CreateServletTemplateModel.ATT_DESCRIPTION)) { 
					String value = (String) params.get(key);

    stringBuffer.append( key );
    stringBuffer.append(TEXT_14);
    stringBuffer.append( value );
    stringBuffer.append(TEXT_10);
    
				} else if (key.equals(CreateServletTemplateModel.ATT_URL_PATTERNS)) {

    stringBuffer.append( key );
    stringBuffer.append(TEXT_15);
    
					List<String[]> mappings = (List<String[]>) params.get(key);
					boolean needComma2 = false;
					boolean needNewLine2 = mappings.size() > 1;
					for (String[] mapping : mappings) {
						if (needComma2) {

    stringBuffer.append(TEXT_9);
    
						}
				
						if (needNewLine2) {

    stringBuffer.append(TEXT_16);
    
						} 

    stringBuffer.append(TEXT_10);
    stringBuffer.append( mapping[0] );
    stringBuffer.append(TEXT_10);
    				
						needComma2 = true;
					}
				
					if (needNewLine2) {

    stringBuffer.append(TEXT_13);
    
					} else {

    stringBuffer.append(TEXT_17);
    
					}

    stringBuffer.append(TEXT_18);
    
				} else if (key.equals(CreateServletTemplateModel.ATT_INIT_PARAMS)) {

    stringBuffer.append( key );
    stringBuffer.append(TEXT_15);
    
					List<String[]> initParams = (List<String[]>) params.get(key);
					boolean needComma2 = false;
					for (String[] initParam : initParams) {
						if (needComma2) {

    stringBuffer.append(TEXT_9);
    
						}
						
						String name = initParam[CreateServletTemplateModel.NAME];
						String value = initParam[CreateServletTemplateModel.VALUE];
						String description = initParam[CreateServletTemplateModel.DESCRIPTION];

    stringBuffer.append(TEXT_19);
    stringBuffer.append( name );
    stringBuffer.append(TEXT_20);
    stringBuffer.append( value );
    stringBuffer.append(TEXT_10);
    				
						if (description != null && description.length() > 0) {

    stringBuffer.append(TEXT_21);
    stringBuffer.append( description );
    stringBuffer.append(TEXT_10);
    
						}

    stringBuffer.append(TEXT_22);
    
						needComma2 = true;
					}

    stringBuffer.append(TEXT_23);
    
				}
				
				else if (key.equals(CreateServletTemplateModel.ATT_ASYNC_SUPPORT)) {
					Boolean value =(Boolean) params.get(key);
				    if (value){

    stringBuffer.append( key );
    stringBuffer.append(TEXT_24);
    stringBuffer.append( value );
    				    }				
				}
				needComma = true;
  			}

    stringBuffer.append(TEXT_22);
    
		}
	}

    
	if (model.isPublic()) { 

    stringBuffer.append(TEXT_25);
     
	} 

	if (model.isAbstract()) { 

    stringBuffer.append(TEXT_26);
    
	}

	if (model.isFinal()) {

    stringBuffer.append(TEXT_27);
    
	}

    stringBuffer.append(TEXT_28);
    stringBuffer.append( model.getClassName() );
    
	String superClass = model.getSuperclassName();
 	if (superClass != null && superClass.length() > 0) {

    stringBuffer.append(TEXT_29);
    stringBuffer.append( superClass );
    
	}

	List<String> interfaces = model.getInterfaces(); 
 	if ( interfaces.size() > 0) { 

    stringBuffer.append(TEXT_30);
    
	}
	
 	for (int i = 0; i < interfaces.size(); i++) {
   		String INTERFACE = interfaces.get(i);
   		if (i > 0) {

    stringBuffer.append(TEXT_9);
    
		}

    stringBuffer.append( INTERFACE );
    
	}

    stringBuffer.append(TEXT_31);
     
	if (model.isGenericServletSuperclass()) { 

    stringBuffer.append(TEXT_32);
     
	} 

     
	if (!model.hasEmptySuperclassConstructor()) { 

    stringBuffer.append(TEXT_33);
    stringBuffer.append( model.getClassName() );
    stringBuffer.append(TEXT_34);
     
	} 

	if (model.shouldGenSuperclassConstructors()) {
		List<Constructor> constructors = model.getConstructors();
		for (Constructor constructor : constructors) {
			if (constructor.isPublic() || constructor.isProtected()) { 

    stringBuffer.append(TEXT_35);
    stringBuffer.append( model.getClassName() );
    stringBuffer.append(TEXT_12);
    stringBuffer.append( constructor.getParamsForDeclaration() );
    stringBuffer.append(TEXT_36);
    stringBuffer.append( constructor.getParamsForCall() );
    stringBuffer.append(TEXT_37);
    
			} 
		} 
	} 

    
	if (model.shouldImplementAbstractMethods()) {
		for (Method method : model.getUnimplementedMethods()) { 

    stringBuffer.append(TEXT_33);
    stringBuffer.append( method.getReturnType() );
    stringBuffer.append(TEXT_17);
    stringBuffer.append( method.getName() );
    stringBuffer.append(TEXT_12);
    stringBuffer.append( method.getParamsForDeclaration() );
    stringBuffer.append(TEXT_38);
      if (method.getExceptions().length() > 0){ 
    stringBuffer.append(TEXT_39);
    stringBuffer.append(method.getExceptions());
    stringBuffer.append(TEXT_40);
    }else {
    stringBuffer.append(TEXT_40);
     } 
    stringBuffer.append(TEXT_41);
     
			String defaultReturnValue = method.getDefaultReturnValue();
			if (defaultReturnValue != null) { 

    stringBuffer.append(TEXT_42);
    stringBuffer.append( defaultReturnValue );
    stringBuffer.append(TEXT_2);
    
			} 

    stringBuffer.append(TEXT_43);
     
		}
	} 

     if (model.shouldGenInit()) { 
    stringBuffer.append(TEXT_44);
     } 
     if (model.shouldGenDestroy()) { 
    stringBuffer.append(TEXT_45);
     } 
     if (model.shouldGenGetServletConfig()) { 
    stringBuffer.append(TEXT_46);
     } 
     if (model.shouldGenGetServletInfo()) { 
    stringBuffer.append(TEXT_47);
     } 
     if (model.shouldGenService() && !model.isHttpServletSuperclass()) { 
    stringBuffer.append(TEXT_48);
     } 
     if (model.shouldGenService() && model.isHttpServletSuperclass()) { 
    stringBuffer.append(TEXT_49);
     } 
     if (model.shouldGenDoGet()) { 
    stringBuffer.append(TEXT_50);
     } 
     if (model.shouldGenDoPost()) { 
    stringBuffer.append(TEXT_51);
     if (model.shouldGenDoGet()) { 
    stringBuffer.append(TEXT_52);
     } 
    stringBuffer.append(TEXT_53);
     } 
     if (model.shouldGenDoPut()) { 
    stringBuffer.append(TEXT_54);
     } 
     if (model.shouldGenDoDelete()) { 
    stringBuffer.append(TEXT_55);
     } 
     if (model.shouldGenDoHead()) { 
    stringBuffer.append(TEXT_56);
     } 
     if (model.shouldGenDoOptions()) { 
    stringBuffer.append(TEXT_57);
     } 
     if (model.shouldGenDoTrace()) { 
    stringBuffer.append(TEXT_58);
     } 
    stringBuffer.append(TEXT_59);
    stringBuffer.append(TEXT_3);
    return stringBuffer.toString();
  }
}
