package hut.cwp.compiler;

import hut.cwp.annotations.InitAttrConfig;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;


public class ContainerProxyInfo implements IProxyInfo {

    private static final String PROXY = "ComponentInject";
    private String packageName;
    private String className;
    private String proxyClassName;
    private TypeElement typeElement;
    private ProcessingEnvironment processingEnv;
    private List<InitAttrConfig> configs = new ArrayList<>();


    public ContainerProxyInfo(Elements elementUtils, TypeElement classElement, ProcessingEnvironment processingEnv) {
        this.typeElement = classElement;
        this.processingEnv = processingEnv;
        this.packageName = elementUtils.getPackageOf(classElement).getQualifiedName().toString();
        this.className = ClassValidator.getClassName(classElement, packageName);
        this.proxyClassName = className + "$$" + PROXY;
    }

    @Override
    public String getProxyClassFullName() {
        return packageName+"."+proxyClassName;
    }

    @Override
    public TypeElement getTypeElement() {
        return typeElement;
    }

    @Override
    public String generateJavaCode() {
        StringBuilder builder = new StringBuilder();
        builder.append("// Generated code. Do not modify!\n");
        builder.append("package ").append(packageName).append(";\n\n");
        builder.append("import hut.cwp.api.Inject;\n"); //这个地方怎样写好呢？
        builder.append("import " + packageName + "." + className + ";\n");
        builder.append('\n');
        builder.append("public class ").append(proxyClassName).append(" implements " + "Inject" + "<").append(className).append(">").append(" {\n");
        generateMethods(builder);
        builder.append('\n');
        builder.append("}\n");
        return builder.toString();
    }

    @Override
    public void generateMethods(StringBuilder builder) {
        builder.append("\n  @Override\n ");
        builder.append(" public void inject( " + className + " host, Object source ) {\n");
        generateComponent(builder);
        builder.append("  }\n");
    }

    private void generateComponent(StringBuilder builder) {
        for (InitAttrConfig config : configs) {
            try {
                config.component();
            } catch (MirroredTypeException e) {
                TypeMirror typeMirror = e.getTypeMirror();
                /**
                 * 通过这个方法来获取具体实现类型！！！
                 */
                Types typeUtils = processingEnv.getTypeUtils();
                TypeElement classTypeElement = (TypeElement) typeUtils.asElement(typeMirror);
                builder.append("     host.autoLoadComponent( +" + config.resourceId() + ", new " + classTypeElement.getQualifiedName() + "() );\n");
            }
        }
    }

    void setConfigs(List<InitAttrConfig> configs) {
        this.configs = configs;
    }

}