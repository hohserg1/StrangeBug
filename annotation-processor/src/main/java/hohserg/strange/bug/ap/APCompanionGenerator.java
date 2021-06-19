package hohserg.strange.bug.ap;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.util.Set;

import static javax.lang.model.element.Modifier.PUBLIC;

@SupportedAnnotationTypes(APCompanionGenerator.Mark_name)
public class APCompanionGenerator extends AbstractProcessor {

    public static final String Mark_name = "hohserg.strange.bug.Mark";

    public Filer filer;
    public Types types;
    public Elements elements;
    public Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
        types = processingEnv.getTypeUtils();
        elements = processingEnv.getElementUtils();
        messager = processingEnv.getMessager();
        messager.printMessage(Diagnostic.Kind.WARNING, "APCompanionGenerator#init");
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        messager.printMessage(Diagnostic.Kind.WARNING, "APCompanionGenerator#process "+annotations);
        TypeElement Mark_element = elements.getTypeElement(Mark_name);
        if (annotations.contains(Mark_element)) {
            roundEnv.getElementsAnnotatedWith(Mark_element)
                    .forEach(e -> {
                        TypeSpec companion = TypeSpec.classBuilder(e.getSimpleName() + "Companion")
                                .addModifiers(PUBLIC)
                                .build();

                        try {
                            JavaFile.builder(elements.getPackageOf(e).getQualifiedName().toString(), companion).build().writeTo(filer);
                        } catch (IOException exception) {
                            messager.printMessage(Diagnostic.Kind.ERROR, "Exception on writing companion class\n" + exception.toString());
                        }
                    });

            return true;
        } else
            return false;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
