package com.exactaworks.testejava.builder;

import com.exactaworks.testejava.model.Spent;
import java.time.OffsetDateTime;
import java.util.Set;

public class SpentBuilder {

        private Spent spent;

        private SpentBuilder() {}

        public static SpentBuilder oneSpent(){
            SpentBuilder builder = new SpentBuilder();
            builder.spent = new Spent();
            builder.spent.setId(1l);
            builder.spent.setPersonName("Fulano que n�o vai dar erro");
            builder.spent.setDescription("Gasto 1 que n�o vai dar erro");
            builder.spent.setValue(9.99);
            builder.spent.setTags(Set.of("Tag 1", "Tag 2","Tag 3"));
            return builder;
        }

        public SpentBuilder withId(Long id){
            this.spent.setId(id);
            return this;
        }

        public SpentBuilder withPersonName(String name){
            this.spent.setPersonName(name);
            return this;
        }
        public SpentBuilder withDescription(String desc){
            this.spent.setDescription(desc);
            return this;
        }
        public SpentBuilder withValue(Double value){
            this.spent.setValue(value);
            return this;
        }
        public SpentBuilder withDateTime(OffsetDateTime datetime){
            this.spent.setDatetime(datetime);
            return this;
        }
        public SpentBuilder withTags(Set<String> tags){
            this.spent.setTags(tags);
            return this;
        }

        public Spent build() {
            return spent;
        }
}

