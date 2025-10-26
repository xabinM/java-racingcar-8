package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {

    private static final String REQUEST_NAME_MESSAGE = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String REQUEST_COUNT_MESSAGE = "시도할 횟수는 몇 회인가요?";

    private static final String DELIMITER = ",";

    private static final String PRINT_RUN_RESULT_MESSAGE = "실행 결과";
    private static final String PRINT_NAME_POSITION_SEPARATOR = " : ";
    private static final String PRINT_POSITION_MARKER = "-";
    private static final String PRINT_WINNER_MESSAGE = "최종 우승자 : ";
    private static final String PRINT_WINNER_DELIMITER = ", ";

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

    public static void main(String[] args) {

        List<String> names = getInputNames();
        int count = getInputCount();

        List<Car> cars = createCars(names);

        runRace(count, cars);
        printWinner(cars);
    }

    private static List<String> getInputNames() {
        System.out.println(REQUEST_NAME_MESSAGE);
        String inputNames = Console.readLine();

        return Arrays.stream(inputNames.split(DELIMITER)).toList();
    }

    private static int getInputCount() {
        System.out.println(REQUEST_COUNT_MESSAGE);
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

    private static void runRace(int count, List<Car> cars) {
        System.out.println(PRINT_RUN_RESULT_MESSAGE);
        for (int i = 0; i < count; i++) {
            updateCarsAdvance(cars);
            printCarsPosition(cars);
        }
    }

    private static void updateCarsAdvance(List<Car> cars) {
        for (Car car : cars) {
            int randomNum = Randoms.pickNumberInRange(0, 9);

            if (randomNum >= 4) {
                car.increaseAdvance();
            }
        }
    }

    private static void printCarsPosition(List<Car> cars) {
        for (Car car : cars) {
            System.out.println(car.name + PRINT_NAME_POSITION_SEPARATOR + PRINT_POSITION_MARKER.repeat(car.advance));
        }
        System.out.println();
    }

    private static void printWinner(List<Car> cars) {
        int maxAdvance = getMaxAdvance(cars);
        List<String> winners = getWinners(cars, maxAdvance);

        System.out.println(PRINT_WINNER_MESSAGE + String.join(PRINT_WINNER_DELIMITER, winners));
    }


    private static int getMaxAdvance(List<Car> cars) {
        int maxAdvance = 0;
        for (Car car : cars) {
            if (car.advance > maxAdvance) {
                maxAdvance = car.advance;
            }
        }

        return maxAdvance;
    }

    private static List<String> getWinners(List<Car> cars, int maxAdvance) {
        List<String> winners = new ArrayList<>();
        for (Car car : cars) {
            if (car.advance == maxAdvance) {
                winners.add(car.name);
            }
        }

        return winners;
    }
}
