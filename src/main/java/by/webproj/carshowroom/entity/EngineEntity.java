package by.webproj.carshowroom.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class EngineEntity {
    private final Long id;
    private final String engineName;
    private final double engineWeight;

    private EngineEntity(Builder builder) {
        id = builder.id;
        engineName = builder.engineName;
        engineWeight = builder.engineWeight;
    }

    public static class Builder {
        private Long id;
        private String engineName;
        private double engineWeight;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String engineName) {
            this.engineName = engineName;
            return this;
        }

        public Builder withWeight(double engineWeight) {
            this.engineWeight = engineWeight;
            return this;
        }

        public EngineEntity build() {
            return new EngineEntity(this);
        }
    }
}
