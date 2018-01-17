package service;

public class Test2Service {

    private TestService testService;

    public Test2Service(){}

    public Test2Service(TestService testService) {
        this.testService = testService;
    }

    public TestService getTestService() {
        return testService;
    }

    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    public void service(){
        System.out.println("test2 service");
        testService.printValue();
    }
}
