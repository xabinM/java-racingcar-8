package racingcar;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String inputNames = Console.readLine();

        System.out.println("시도할 횟수는 몇 회인가요?");
        String inputCount = Console.readLine();

        List<String> names = Arrays.stream(inputNames.split(",")).toList();
        int count = Integer.parseInt(inputCount);

        List<Car> cars = new ArrayList<>();
        for (String name : names) {
            cars.add(new Car(name, 0));
        }
    }

    private static class Car {
        String name;
        int advance;

        public Car(String name, int advance) {
            this.name = name;
            this.advance = advance;
        }
    }
}
