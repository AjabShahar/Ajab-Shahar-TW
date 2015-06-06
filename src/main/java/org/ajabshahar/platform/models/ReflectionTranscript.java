package org.ajabshahar.platform.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;

@Entity
@Table(name = "TRANSCRIPT")
    public class ReflectionTranscript {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "HINDI_TRANSCRIPT")
    private String hindiTranscript;

    @Column(name = "ENGLISH_TRANSCRIPT")
    private String englishTranscript;

    @ManyToOne
    @JoinColumn(name = "reflection_id", nullable = false)
    @JsonBackReference
    private Reflection reflection;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHindiTranscript() {
        return hindiTranscript;
    }

    public void setHindiTranscript(String hindiTranscript) {
        this.hindiTranscript = hindiTranscript;
    }

    public Reflection getReflection() {
        return reflection;
    }

    public void setReflection(Reflection reflection) {
        this.reflection = reflection;
    }

    public String getEnglishTranscript() {
        return englishTranscript;
    }

    public void setEnglishTranscript(String englishTranscript) {
        this.englishTranscript = englishTranscript;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReflectionTranscript that = (ReflectionTranscript) o;

        if (id != that.id) return false;
        if (englishTranscript != null ? !englishTranscript.equals(that.englishTranscript) : that.englishTranscript != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (englishTranscript != null ? englishTranscript.hashCode() : 0);
        return result;
    }
}
