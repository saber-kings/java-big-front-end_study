package org.eclipse.jst.j2ee.internal.web.operations;

import java.util.*;
import org.eclipse.jst.j2ee.internal.common.operations.*;

public class FilterTemplate
{
  protected static String nl;
  public static synchronized FilterTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    FilterTemplate result = new FilterTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "package ";
  protected final String TEXT_2 = ";";
  protected final String TEXT_3 = NL;
  protected final String TEXT_4 = NL + "import ";
  protected final String TEXT_5 = NL + " *    description=\"";
  protected final String TEXT_6 = "\"";
  protected final String TEXT_7 = NL + "@WebFilter";
  protected final String TEXT_8 = "(\"";
  protected final String TEXT_9 = "\")";
  protected final String TEXT_10 = "({ ";
  protected final String TEXT_11 = ", ";
  protected final String TEXT_12 = " })";
  protected final String TEXT_13 = "(";
  protected final String TEXT_14 = NL + "\t\t";
  protected final String TEXT_15 = " = \"";
  protected final String TEXT_16 = " = { ";
  protected final String TEXT_17 = NL + "\t\t\t\t";
  protected final String TEXT_18 = " ";
  protected final String TEXT_19 = "}";
  protected final String TEXT_20 = NL + "\t\t\t\t@WebInitParam(name = \"";
  protected final String TEXT_21 = "\", value = \"";
  protected final String TEXT_22 = ", description = \"";
  protected final String TEXT_23 = ")";
  protected final String TEXT_24 = NL + "\t\t}";
  protected final String TEXT_25 = " = {";
  protected final String TEXT_26 = "}" + NL + "\t\t\t\t\t";
  protected final String TEXT_27 = " = ";
  protected final String TEXT_28 = NL + "public ";
  protected final String TEXT_29 = "abstract ";
  protected final String TEXT_30 = "final ";
  protected final String TEXT_31 = "class ";
  protected final String TEXT_32 = " extends ";
  protected final String TEXT_33 = " implements ";
  protected final String TEXT_34 = " {";
  protected final String TEXT_35 = NL + NL + "    public ";
  protected final String TEXT_36 = "() {" + NL + "    }";
  protected final String TEXT_37 = NL + "       " + NL + "    public ";
  protected final String TEXT_38 = ") {" + NL + "        super(";
  protected final String TEXT_39 = ");" + NL + "    }";
  protected final String TEXT_40 = ") ";
  protected final String TEXT_41 = "throws ";
  protected final String TEXT_42 = " { ";
  protected final String TEXT_43 = NL + "         // TODO Auto-generated method stub";
  protected final String TEXT_44 = NL + "\t\t\treturn ";
  protected final String TEXT_45 = NL + "    }";
  protected final String TEXT_46 = NL + "\t@Override" + NL + "\tpublic void destroy() {" + NL + "\t}";
  protected final String TEXT_47 = NL + "\t@Override" + NL + "\tpublic void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {" + NL + "\t\tchain.doFilter(request, response);" + NL + "\t}";
  protected final String TEXT_48 = NL + "\t@Override" + NL + "\tpublic void init(FilterConfig fConfig) throws ServletException {" + NL + "\t}";
  protected final String TEXT_49 = NL + NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     CreateFilterTemplateModel model = (CreateFilterTemplateModel) argument; 
    
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
		
		List<IFilterMappingItem> mappings = model.getFilterMappings();
 		for (IFilterMappingItem mapping : mappings) { 

    
			if (mapping.isUrlPatternType()) { 

    
			} else if (mapping.isServletNameType()) { 

    
			}
		 
			String dispatcher = model.getDispatcherList(mapping);
			if (dispatcher.length() > 0) { 

     
			} 
		} 

		List<String[]> initParams = model.getInitParams();
		if (initParams != null && initParams.size() > 0) {
			for (int i = 0; i < initParams.size(); i++) {
				String name = model.getInitParam(i, CreateFilterTemplateModel.NAME);
				String value = model.getInitParam(i, CreateFilterTemplateModel.VALUE);
				String description = model.getInitParam(i, CreateFilterTemplateModel.DESCRIPTION); 

    
				if (description != null && description.length() > 0) { 

    stringBuffer.append(TEXT_5);
    stringBuffer.append( description );
    stringBuffer.append(TEXT_6);
    
				}
			} 
		} 
	}

     
	if ("3.0".equals(model.getJavaEEVersion()) || "3.1".equals(model.getJavaEEVersion()) || "4.0".equals(model.getJavaEEVersion())) { 

    stringBuffer.append(TEXT_7);
    
		Map<String, Object> params = model.getClassAnnotationParams();
		if (params.size() == 1 && params.containsKey(CreateServletTemplateModel.ATT_URL_PATTERNS)) {
			List<String> mappings = (List<String>) params.get(CreateServletTemplateModel.ATT_URL_PATTERNS);
			if (mappings.size() == 1) {
				String value = mappings.get(0);

    stringBuffer.append(TEXT_8);
    stringBuffer.append( value );
    stringBuffer.append(TEXT_9);
    
			} else {

    stringBuffer.append(TEXT_10);
    
				boolean needComma = false;
				for (String mapping : mappings) {
					if (needComma) {

    stringBuffer.append(TEXT_11);
    
					}

    stringBuffer.append(TEXT_6);
    stringBuffer.append( mapping );
    stringBuffer.append(TEXT_6);
    
					needComma = true;
				}

    stringBuffer.append(TEXT_12);
    
			}
		} else if (!params.isEmpty()) { 

    stringBuffer.append(TEXT_13);
    
			Set<String> keys = params.keySet();
			boolean needNewLine = keys.contains(CreateFilterTemplateModel.ATT_INIT_PARAMS) || 
					(keys.contains(CreateFilterTemplateModel.ATT_URL_PATTERNS) && 
							((List<String>) params.get(CreateFilterTemplateModel.ATT_URL_PATTERNS)).size() > 1) || 
					(keys.contains(CreateFilterTemplateModel.ATT_SERVLET_NAMES) && 
							((List<String>) params.get(CreateFilterTemplateModel.ATT_SERVLET_NAMES)).size() > 1);
			boolean needComma = false;
			for (String key : keys) {
				if (needComma) {

    stringBuffer.append(TEXT_11);
    
				}
				
				if (needNewLine) {

    stringBuffer.append(TEXT_14);
    
				} 
			
				if (key.equals(CreateFilterTemplateModel.ATT_FILTER_NAME) || key.equals(CreateFilterTemplateModel.ATT_DESCRIPTION)) { 
					String value = (String) params.get(key);

    stringBuffer.append( key );
    stringBuffer.append(TEXT_15);
    stringBuffer.append( value );
    stringBuffer.append(TEXT_6);
    
				} else if (key.equals(CreateFilterTemplateModel.ATT_URL_PATTERNS)) {

    stringBuffer.append( key );
    stringBuffer.append(TEXT_16);
    
					List<String> mappings = (List<String>) params.get(key);
					boolean needComma2 = false;
					boolean needNewLine2 = mappings.size() > 1;
					for (String mapping : mappings) {
						if (needComma2) {

    stringBuffer.append(TEXT_11);
    
						}
				
						if (needNewLine2) {

    stringBuffer.append(TEXT_17);
    
						} 

    stringBuffer.append(TEXT_6);
    stringBuffer.append( mapping );
    stringBuffer.append(TEXT_6);
    				
						needComma2 = true;
					}
				
					if (needNewLine2) {

    stringBuffer.append(TEXT_14);
    
					} else {

    stringBuffer.append(TEXT_18);
    
					}

    stringBuffer.append(TEXT_19);
    
				} else if (key.equals(CreateFilterTemplateModel.ATT_SERVLET_NAMES)) {

    stringBuffer.append( key );
    stringBuffer.append(TEXT_16);
    
					List<String> servletNames = (List<String>) params.get(key);
					boolean needComma2 = false;
					boolean needNewLine2 = servletNames.size() > 1;
					for (String servletName : servletNames) {
						if (needComma2) {

    stringBuffer.append(TEXT_11);
    
						}
				
						if (needNewLine2) {

    stringBuffer.append(TEXT_17);
    
						} 

    stringBuffer.append(TEXT_6);
    stringBuffer.append( servletName );
    stringBuffer.append(TEXT_6);
    				
						needComma2 = true;
					}
				
					if (needNewLine2) {

    stringBuffer.append(TEXT_14);
    
					} else {

    stringBuffer.append(TEXT_18);
    
					}

    stringBuffer.append(TEXT_19);
    
				} else if (key.equals(CreateFilterTemplateModel.ATT_INIT_PARAMS)) {

    stringBuffer.append( key );
    stringBuffer.append(TEXT_16);
    
					List<String[]> initParams = (List<String[]>) params.get(key);
					boolean needComma2 = false;
					for (String[] initParam : initParams) {
						if (needComma2) {

    stringBuffer.append(TEXT_11);
    
						}
						
						String name = initParam[CreateFilterTemplateModel.NAME];
						String value = initParam[CreateFilterTemplateModel.VALUE];
						String description = initParam[CreateFilterTemplateModel.DESCRIPTION];

    stringBuffer.append(TEXT_20);
    stringBuffer.append( name );
    stringBuffer.append(TEXT_21);
    stringBuffer.append( value );
    stringBuffer.append(TEXT_6);
    				
						if (description != null && description.length() > 0) {

    stringBuffer.append(TEXT_22);
    stringBuffer.append( description );
    stringBuffer.append(TEXT_6);
    
						}

    stringBuffer.append(TEXT_23);
    
						needComma2 = true;
					}

    stringBuffer.append(TEXT_24);
    
				} else if (key.equals(CreateFilterTemplateModel.ATT_DISPATCHER_TYPES)) {
				List<String> dispatcherTypes = (List<String>) params.get(key);
					boolean needComma2 = false;
					boolean needNewLine2 = dispatcherTypes.size() > 1;
					if (dispatcherTypes.size()>0){
					
    stringBuffer.append( key );
    stringBuffer.append(TEXT_25);
     
					}
					for (String dispType : dispatcherTypes) {
						if (needComma2) {

    stringBuffer.append(TEXT_11);
    
						}
				
						if (needNewLine2) {

    stringBuffer.append(TEXT_17);
    
						} 

    stringBuffer.append( dispType );
    				
						needComma2 = true;
					}
				
					if (needNewLine2) {

    stringBuffer.append(TEXT_14);
    
					} else {

    stringBuffer.append(TEXT_18);
    
					}
					
    stringBuffer.append(TEXT_26);
    
				}
				else if (key.equals(CreateFilterTemplateModel.ATT_ASYNC_SUPPORT)) {
					Boolean value =(Boolean) params.get(key);
				    if (value){

    stringBuffer.append( key );
    stringBuffer.append(TEXT_27);
    stringBuffer.append( value );
    				    }				
				}				
				needComma = true;
  			}

    stringBuffer.append(TEXT_23);
    
		}
	}

    
	if (model.isPublic()) { 

    stringBuffer.append(TEXT_28);
     
	} 

	if (model.isAbstract()) { 

    stringBuffer.append(TEXT_29);
    
	}

	if (model.isFinal()) {

    stringBuffer.append(TEXT_30);
    
	}

    stringBuffer.append(TEXT_31);
    stringBuffer.append( model.getClassName() );
    
	String superClass = model.getSuperclassName();
 	if (superClass != null && superClass.length() > 0) {

    stringBuffer.append(TEXT_32);
    stringBuffer.append( superClass );
    
	}

	List<String> interfaces = model.getInterfaces(); 
 	if ( interfaces.size() > 0) { 

    stringBuffer.append(TEXT_33);
    
	}
	
 	for (int i = 0; i < interfaces.size(); i++) {
   		String INTERFACE = interfaces.get(i);
   		if (i > 0) {

    stringBuffer.append(TEXT_11);
    
		}

    stringBuffer.append( INTERFACE );
    
	}

    stringBuffer.append(TEXT_34);
     
	if (!model.hasEmptySuperclassConstructor()) { 

    stringBuffer.append(TEXT_35);
    stringBuffer.append( model.getClassName() );
    stringBuffer.append(TEXT_36);
     
	} 

	if (model.shouldGenSuperclassConstructors()) {
		List<Constructor> constructors = model.getConstructors();
		for (Constructor constructor : constructors) {
			if (constructor.isPublic() || constructor.isProtected()) { 

    stringBuffer.append(TEXT_37);
    stringBuffer.append( model.getClassName() );
    stringBuffer.append(TEXT_13);
    stringBuffer.append( constructor.getParamsForDeclaration() );
    stringBuffer.append(TEXT_38);
    stringBuffer.append( constructor.getParamsForCall() );
    stringBuffer.append(TEXT_39);
    
			} 
		} 
	} 

    
	if (model.shouldImplementAbstractMethods()) {
		for (Method method : model.getUnimplementedMethods()) { 

    stringBuffer.append(TEXT_35);
    stringBuffer.append( method.getReturnType() );
    stringBuffer.append(TEXT_18);
    stringBuffer.append( method.getName() );
    stringBuffer.append(TEXT_13);
    stringBuffer.append( method.getParamsForDeclaration() );
    stringBuffer.append(TEXT_40);
      if (method.getExceptions().length() > 0){ 
    stringBuffer.append(TEXT_41);
    stringBuffer.append(method.getExceptions());
    stringBuffer.append(TEXT_42);
    }else {
    stringBuffer.append(TEXT_42);
     } 
    stringBuffer.append(TEXT_43);
     
			String defaultReturnValue = method.getDefaultReturnValue();
			if (defaultReturnValue != null) { 

    stringBuffer.append(TEXT_44);
    stringBuffer.append( defaultReturnValue );
    stringBuffer.append(TEXT_2);
    
			} 

    stringBuffer.append(TEXT_45);
     
		}
	} 

     if (model.shouldGenDestroy()) { 
    stringBuffer.append(TEXT_46);
     } 
     if (model.shouldGenDoFilter()) { 
    stringBuffer.append(TEXT_47);
     } 
     if (model.shouldGenInit()) { 
    stringBuffer.append(TEXT_48);
     } 
    stringBuffer.append(TEXT_49);
    stringBuffer.append(TEXT_3);
    return stringBuffer.toString();
  }
}
