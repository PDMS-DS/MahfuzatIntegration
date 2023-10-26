package com.dataserve.archivemanagement.model.keys;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EDSChoicesCompositeKey implements Serializable {
    @Column(name = "PROPERTY")
    private String property;
    @Column(name = "OBJECTTYPE")
    private String classSymbolicName;
    @Column(name = "LANG")
    private String lang;
    @Column(name = "VALUE")
    private String value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EDSChoicesCompositeKey that = (EDSChoicesCompositeKey) o;
        return Objects.equals(property, that.property) &&
                Objects.equals(classSymbolicName, that.classSymbolicName) && Objects.equals(lang, that.lang)
                && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(property, classSymbolicName);
    }


}
