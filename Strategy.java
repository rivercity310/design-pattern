import java.util.*;


public class Strategy {
    static interface FlyBehavior {
        void fly();
    }

    static class FlyWithWings implements FlyBehavior {
        public void fly() {
            System.out.println("FLY WITH WINGS");
        }
    }

    static class FlyRocketPowered implements FlyBehavior {
        public void fly() {
            System.out.println("FLY ROCKET POWERED");
        }
    }

    static interface QuackBehavior {
        void quack();
    }

    static class MuteQuack implements QuackBehavior {
        public void quack() {
            System.out.println("MUTE QUACK");
        }
    }

    static class Quack implements QuackBehavior {
        public void quack() {
            System.out.println("QUACK");
        }
    }
    
    static abstract class Duck {
        protected FlyBehavior flyBehavior;
        protected QuackBehavior quackBehavior;

        public void setFlyBehavior(FlyBehavior fb) {
            this.flyBehavior = fb;
        }

        public FlyBehavior getFlyBehavior() {
            return this.flyBehavior;
        }

        public void setQuackBehavior(QuackBehavior qb) {
            this.quackBehavior = qb;
        }

        public QuackBehavior getQuackBehavior() {
            return this.quackBehavior;
        }

        public void performFly() {
            this.flyBehavior.fly();
        }

        public void performQuack() {
            this.quackBehavior.quack();
        }

        public void swim() {
            System.out.println("SWIM");
        }
    }

    static class PaperDuck extends Duck {
        public PaperDuck() {
            this.flyBehavior = new FlyWithWings();
            this.quackBehavior = new MuteQuack();
        }
    }

    static class SuperDuck extends Duck {
        public SuperDuck() {
            this.flyBehavior = new FlyRocketPowered();
            this.quackBehavior = new Quack();
        }
    }

    public static void main(String[] args) {
        List<Duck> ducks = new ArrayList<>();
        ducks.add(new SuperDuck());
        ducks.add(new PaperDuck());

        for (Duck duck : ducks) {
            duck.performQuack();
            duck.performFly();

            FlyBehavior flyBehavior = duck.getFlyBehavior();
            QuackBehavior quackBehavior = duck.getQuackBehavior();

            try {
                Boolean isWings = Class.forName(FlyWithWings.class.getName()).isInstance(flyBehavior);
                Boolean isMute = Class.forName(MuteQuack.class.getName()).isInstance(quackBehavior);
                duck.setFlyBehavior(isWings ? new FlyRocketPowered() : new FlyWithWings());
                duck.setQuackBehavior(isMute ? new Quack() : new MuteQuack());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        for (Duck duck : ducks) {
            duck.performQuack();
            duck.performFly();
        }
    }
}