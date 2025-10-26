package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        List<String> names = getInputNames();
        int count = getInputCount();

        List<Car> cars = createCars(names);

        // 자동차 경주 시뮬레이션
        System.out.println("실행 결과");
        for (int i = 0; i < count; i++) {
            for (Car car : cars) {
                int randValue = Randoms.pickNumberInRange(0, 9);

                if (randValue >= 4) {
                    car.increaseAdvance();
                }

                System.out.print(car.name + " : ");
                for (int j = 0; j < car.advance; j++) {
                    System.out.print("-");
                }
                System.out.println();
            }
            System.out.println();
        }

        // 최종 우승자 출력
        int maxAdvance = 0;

        for (Car car : cars) {
            if (car.advance > maxAdvance) {
                maxAdvance = car.advance;
            }
        }

        List<String> winners = new ArrayList<>();
        for (Car car : cars) {
            if (car.advance == maxAdvance) {
                winners.add(car.name);
            }
        }

        System.out.println("최종 우승자 : " + String.join(", ", winners));
    }

    private static class Car {
        String name;
        int advance;

        public Car(String name, int advance) {
            this.name = name;
            this.advance = advance;
        }

        public void increaseAdvance() {
            this.advance++;
        }
    }

    private static List<String> getInputNames() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String inputNames = Console.readLine();

        return Arrays.stream(inputNames.split(",")).toList();
    }

    private static int getInputCount() {
        System.out.println("시도할 횟수는 몇 회인가요?");
        String inputCount = Console.readLine();

        return Integer.parseInt(inputCount);
    }

    private static List<Car> createCars(List<String> names) {
        List<Car> cars = new ArrayList<>();

        for (String name : names) {
            cars.add(new Car(name, 0));
        }

        return cars;
    }
}
