package dependencygraph.command;


import dependencygraph.ApplicationConstants;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;


/**
 * The main method that takes customer input and calls the graph manager to render the graph
 *
 * @author Jijo Wilson (jiwilson@expedia.com)
 */
public class DependencyGraphCommand {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        DependencyGraphManager dependencyGraphManager = (DependencyGraphManager) applicationContext.getBean("dependencyGraphManager");

        Scanner in = new Scanner(System.in);

        String input;

        while (true){
            System.out.println(String.format("Enter full path to dependency file Location. Enter %s to quit", ApplicationConstants.QUIT_KEY));
            input = in.nextLine();
            if (ApplicationConstants.QUIT_KEY.equals(input)){
                break;
            }
            String finalResult = dependencyGraphManager.generateDependencyGraph(input);
            System.out.println(finalResult);
        }


        System.out.println("Exiting......");


    }

}
