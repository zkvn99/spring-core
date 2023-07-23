package minwook.core;

import jdk.jshell.tool.JavaShellToolBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HiLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HiLombok hiLombok = new HiLombok();
        hiLombok.setName("asdf");

        System.out.println("HiLombok = " + hiLombok);
    }
}
