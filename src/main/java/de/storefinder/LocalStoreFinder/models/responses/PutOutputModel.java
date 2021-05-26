package de.storefinder.LocalStoreFinder.models.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PutOutputModel<T> {
    private String id;
    private T message;
}
