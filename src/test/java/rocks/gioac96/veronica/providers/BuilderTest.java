package rocks.gioac96.veronica.providers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class BuilderTest {

    @Test
    void testSingleInstance() {

        class SingleInstanceBuilder extends GreetingBuilder implements BuildsSingleInstance {
        }

        assertSame(new SingleInstanceBuilder().build(), new SingleInstanceBuilder().build());

    }

    @Test
    void testMultipleInstances() {

        class MultipleInstancesBuilder extends GreetingBuilder implements BuildsMultipleInstances {
        }

        assertNotSame(new MultipleInstancesBuilder().build(), new MultipleInstancesBuilder().build());
        assertEquals(new MultipleInstancesBuilder().build(), new MultipleInstancesBuilder().build());

    }

    @Test
    void testMultipleConfigurations() {

        abstract class RootBuilder extends Builder<String> implements BuildsMultipleInstances {

            protected String value = "";

            @Override
            protected void configure() {

                value += "RootBuilder";

                super.configure();

            }

            @Override
            protected String instantiate() {
                return value;
            }
        }

        class ChildBuilder extends RootBuilder {

            @Override
            protected void configure() {

                value += "ChildBuilder";

                super.configure();

            }

        }

        assertEquals("ChildBuilderRootBuilder", new ChildBuilder().build());

    }

    private static abstract class GreetingBuilder extends Builder<String> {

        @Override
        protected String instantiate() {
            return new String("Ciao");
        }

    }

}