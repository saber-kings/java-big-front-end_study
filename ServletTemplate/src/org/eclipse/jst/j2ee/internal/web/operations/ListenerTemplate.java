package org.eclipse.jst.j2ee.internal.web.operations;

import java.util.*;
import org.eclipse.jst.j2ee.internal.common.operations.*;

public class ListenerTemplate
{
  protected static String nl;
  public static synchronized ListenerTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ListenerTemplate result = new ListenerTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "package ";
  protected final String TEXT_2 = ";";
  protected final String TEXT_3 = NL;
  protected final String TEXT_4 = NL + "import ";
  protected final String TEXT_5 = NL + "@WebListener";
  protected final String TEXT_6 = NL + "public ";
  protected final String TEXT_7 = "abstract ";
  protected final String TEXT_8 = "final ";
  protected final String TEXT_9 = "class ";
  protected final String TEXT_10 = " extends ";
  protected final String TEXT_11 = " implements ";
  protected final String TEXT_12 = ", ";
  protected final String TEXT_13 = " {";
  protected final String TEXT_14 = NL + NL + "    public ";
  protected final String TEXT_15 = "() {" + NL + "    }";
  protected final String TEXT_16 = NL + "       " + NL + "    public ";
  protected final String TEXT_17 = "(";
  protected final String TEXT_18 = ") {" + NL + "        super(";
  protected final String TEXT_19 = ");" + NL + "    }";
  protected final String TEXT_20 = " ";
  protected final String TEXT_21 = ") ";
  protected final String TEXT_22 = "throws ";
  protected final String TEXT_23 = " { ";
  protected final String TEXT_24 = NL + "         // TODO Auto-generated method stub";
  protected final String TEXT_25 = NL + "\t\t\treturn ";
  protected final String TEXT_26 = NL + "    }";
  protected final String TEXT_27 = NL + "\t" + NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     CreateListenerTemplateModel model = (CreateListenerTemplateModel) argument; 
    
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

     
	}

     
	if ("3.0".equals(model.getJavaEEVersion()) || "3.1".equals(model.getJavaEEVersion()) ||  "4.0".equals(model.getJavaEEVersion())) { 

    stringBuffer.append(TEXT_5);
    
}

    
	if (model.isPublic()) { 

    stringBuffer.append(TEXT_6);
     
	} 

	if (model.isAbstract()) { 

    stringBuffer.append(TEXT_7);
    
	}

	if (model.isFinal()) {

    stringBuffer.append(TEXT_8);
    
	}

    stringBuffer.append(TEXT_9);
    stringBuffer.append( model.getClassName() );
    
	String superClass = model.getSuperclassName();
 	if (superClass != null && superClass.length() > 0) {

    stringBuffer.append(TEXT_10);
    stringBuffer.append( superClass );
    
	}

	List<String> interfaces = model.getInterfaces(); 
 	if ( interfaces.size() > 0) { 

    stringBuffer.append(TEXT_11);
    
	}
	
 	for (int i = 0; i < interfaces.size(); i++) {
   		String INTERFACE = interfaces.get(i);
   		if (i > 0) {

    stringBuffer.append(TEXT_12);
    
		}

    stringBuffer.append( INTERFACE );
    
	}

    stringBuffer.append(TEXT_13);
     
	if (!model.hasEmptySuperclassConstructor()) { 

    stringBuffer.append(TEXT_14);
    stringBuffer.append( model.getClassName() );
    stringBuffer.append(TEXT_15);
     
	} 

	if (model.shouldGenSuperclassConstructors()) {
		List<Constructor> constructors = model.getConstructors();
		for (Constructor constructor : constructors) {
			if (constructor.isPublic() || constructor.isProtected()) { 

    stringBuffer.append(TEXT_16);
    stringBuffer.append( model.getClassName() );
    stringBuffer.append(TEXT_17);
    stringBuffer.append( constructor.getParamsForDeclaration() );
    stringBuffer.append(TEXT_18);
    stringBuffer.append( constructor.getParamsForCall() );
    stringBuffer.append(TEXT_19);
    
			} 
		} 
	} 

    
	if (model.shouldImplementAbstractMethods()) {
		for (Method method : model.getUnimplementedMethods()) { 

    stringBuffer.append(TEXT_14);
    stringBuffer.append( method.getReturnType() );
    stringBuffer.append(TEXT_20);
    stringBuffer.append( method.getName() );
    stringBuffer.append(TEXT_17);
    stringBuffer.append( method.getParamsForDeclaration() );
    stringBuffer.append(TEXT_21);
      if (method.getExceptions().length() > 0){ 
    stringBuffer.append(TEXT_22);
    stringBuffer.append(method.getExceptions());
    stringBuffer.append(TEXT_23);
    }else {
    stringBuffer.append(TEXT_23);
     } 
    stringBuffer.append(TEXT_24);
     
			String defaultReturnValue = method.getDefaultReturnValue();
			if (defaultReturnValue != null) { 

    stringBuffer.append(TEXT_25);
    stringBuffer.append( defaultReturnValue );
    stringBuffer.append(TEXT_2);
    
			} 

    stringBuffer.append(TEXT_26);
     
		}
	} 

    stringBuffer.append(TEXT_27);
    stringBuffer.append(TEXT_3);
    return stringBuffer.toString();
  }
}
