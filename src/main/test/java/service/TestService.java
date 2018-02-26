package service;

public class TestService implements PrintValueService{

    private String testValue;

    public TestService(){}

    public TestService(String testValue) {
        this.testValue = testValue;
    }

    public String getTestValue() {
        return testValue;
    }

    public void setTestValue(String testValue) {
        this.testValue = testValue;
    }

    public void printValue(){
        System.out.println("value: " + testValue);
    }
}
