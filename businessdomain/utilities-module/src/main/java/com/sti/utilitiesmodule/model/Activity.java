package com.sti.utilitiesmodule.model;

import com.sti.utilitiesmodule.model.status.ModelStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Activity class to represent Activity entity.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Entity
@Table(name = "t_activity")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Activity {

    @Id
    @Column(name = "activity_id", nullable = false, unique = true, length = 64)
    private String activityId;

    @Column(name = "activity_name", nullable = false)
    private String activityName;

    @Column(name = "activity_description", nullable = false)
    private String activityDescription;

    @Column(name = "transaction_user", nullable = false)
    private String transactionUser;

    @Column(name = "activity_status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private ModelStatus activityStatus;

    @Column(name = "registration_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    /**
     * Adds fields which are not populated by Activity DTO.
     * @return
     */
    public static Activity buildActivityFromDto(Activity activity){
        activity.setActivityId(UUID.randomUUID().toString());
        activity.setActivityStatus(ModelStatus.ACTIVE);
        return activity;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false;
        if(o.getClass() != this.getClass()) return false;
        Activity activity = (Activity) o;
        return this.activityId == activity.activityId
                && (this.activityName.equals(activity.activityName))
                && (this.activityDescription.equals(activity.activityDescription));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.activityName == null ? 0 : this.activityName.hashCode());
        hash = 31 * hash + (this.activityId == null ? 0 : this.activityId.hashCode());
        hash = 31 * hash + (this.activityDescription == null ? 0 : this.activityDescription.hashCode());
        return hash;
    }
}
