package org.test4j.spec.txt;

import org.junit.After;
import org.test4j.junit.JSpec;
import org.test4j.spec.annotations.Given;
import org.test4j.spec.annotations.Named;
import org.test4j.spec.annotations.StoryFile;
import org.test4j.spec.annotations.StorySource;
import org.test4j.spec.annotations.StoryType;
import org.test4j.spec.annotations.Then;
import org.test4j.spec.annotations.When;
import org.test4j.spec.exceptions.ScenarioAssertError;
import org.test4j.spec.inner.IScenario;

@SuppressWarnings("rawtypes")
@StoryFile(type = StoryType.TXT, source = StorySource.ClassPath)
public class MultiThenErrorSpec extends JSpec {
    Throwable specError = null;

    @Override
    public void runScenario(IScenario scenario) throws Throwable {
        this.errorType = ErrorType.Normal;
        specError = null;
        try {
            super.runScenario(scenario);
        } catch (Throwable e) {
            specError = e;
        }
    }

    @After
    public void checkScenarioAssertError() {
        switch (this.errorType) {
            case ScenarioError:
                want.object(this.specError).clazIs(ScenarioAssertError.class);
                break;
            case Normal:
                want.object(specError).isNull();
                break;
            case Exception:
                Class claz = this.specError.getClass();
                assert claz != ScenarioAssertError.class;
                break;
            default:
                throw new RuntimeException("check error");
        }
    }

    @Given
    public void initNormal() throws Exception {
    }

    @When
    public void executeNormal() throws Exception {
    }

    @Then
    public void checkError(final @Named("错误消息") String error// <br>
    ) throws Exception {
        throw new AssertionError(error);
    }

    @Then
    public void checkNormal() throws Exception {
    }

    private ErrorType errorType = ErrorType.Normal;

    @Then
    public void checkScenarioError() throws Exception {
        errorType = ErrorType.ScenarioError;
    }

    @Then
    public void checkScenarioNormal() throws Exception {
        errorType = ErrorType.Normal;
    }

    @Given
    public void checkScenarioException() throws Exception {
        errorType = ErrorType.Exception;
    }

    @Given
    public void initError() throws Exception {
        throw new RuntimeException("normal exception");
    }

    public static enum ErrorType {
        /**
         * 无错误
         */
        Normal,
        /**
         * 多个Then错误
         */
        ScenarioError,
        /**
         * 普通错误
         */
        Exception;
    }
}
