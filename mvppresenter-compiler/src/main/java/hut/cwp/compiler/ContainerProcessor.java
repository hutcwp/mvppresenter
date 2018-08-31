package hut.cwp.compiler;

import hut.cwp.annotations.InitAttrConfigs;

import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.JavaFileObject;


@AutoService(Processor.class)
public class ContainerProcessor extends AbstractProcessor {

    private Logger logger;
    private Messager messager;
    private Elements elementUtils;
    private Map<String, ContainerProxyInfo> mProxyMap = new HashMap<>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
        logger = new Logger(messager);
        elementUtils = processingEnv.getElementUtils();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        HashSet<String> supportTypes = new LinkedHashSet<>();
        supportTypes.add(InitAttrConfigs.class.getCanonicalName());
        return supportTypes;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        logger.info("Container process...");
        mProxyMap.clear();
        parseAnnotation(roundEnvironment);
        generateCode();
        return true;
    }

    private void parseAnnotation(RoundEnvironment roundEnv) {
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(InitAttrConfigs.class);
        for (Element element : elements) {
            TypeElement typeElement = (TypeElement) element;
            String className = typeElement.getQualifiedName().toString(); //完全限定名称
            ContainerProxyInfo proxyInfo = new ContainerProxyInfo(elementUtils, typeElement, processingEnv);
            mProxyMap.put(className, proxyInfo);
            InitAttrConfigs configs = typeElement.getAnnotation(InitAttrConfigs.class);
            proxyInfo.setConfigs(Arrays.asList(configs.value()));
        }
    }

    private void generateCode() {
        //统一进行文件生成
        for (ContainerProxyInfo proxyInfo : mProxyMap.values()) {
            try {
                JavaFileObject jfo = processingEnv.getFiler().createSourceFile(
                        proxyInfo.getProxyClassFullName(),
                        proxyInfo.getTypeElement());
                Writer writer = jfo.openWriter();
                writer.write(proxyInfo.generateJavaCode());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                logger.error("presenter generateCode fail!");
                logger.error(e);
            }
        }
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
