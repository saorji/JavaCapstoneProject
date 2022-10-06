package com.samuelhrm.entities;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
//@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class HrmPatient extends  HrmUser {




    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medicalrecord_id")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private MedicalRecord medicalRecord;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "patient_roles",
          joinColumns = @JoinColumn(name = "patient_id"),
          inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ToString.Exclude
    private Set<Role> roles = new HashSet<>();

    private Long cardNo;
    private String GurrantorContactNumber;
    private String age;
    @Enumerated(EnumType. STRING)
    private Gender sex;
    private String addressNo;
    private String addressStreetName;
    private String AddressStateName;
    private String PhoneNumber;

    @OneToMany
    @JoinColumn(name = "diagnosis_id")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
   private List<Diagnosis> diagnosis;




  public HrmPatient(String username, String email, String encode, String firstName, String lastName) {
    super(username, email, encode, firstName, lastName);
  }



  public Set<Role> getRoles() {
    return roles;
  }



  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public Gender getSex() {
    return sex;
  }

  public void setSex(Gender sex) {
    this.sex = sex;
  }

  public String getAddressNo() {
    return addressNo;
  }

  public void setAddressNo(String addressNo) {
    this.addressNo = addressNo;
  }

  public String getAddressStreetName() {
    return addressStreetName;
  }

  public void setAddressStreetName(String addressStreetName) {
    this.addressStreetName = addressStreetName;
  }

  public String getAddressStateName() {
    return AddressStateName;
  }

//    public List<Appointment> getPatientAppointment() {
//        return patientAppointment;
//    }
//
//    public void setPatientAppointment(List<Appointment> patientAppointment) {
//        this.patientAppointment = patientAppointment;
//    }


    public Long getCardNo() {
        return cardNo;
    }

    public void setCardNo(Long cardNo) {
        this.cardNo = cardNo;
    }

    public String getGurrantorContactNumber() {
        return GurrantorContactNumber;
    }

    public void setGurrantorContactNumber(String gurrantorContactNumber) {
        GurrantorContactNumber = gurrantorContactNumber;
    }

    public void setAddressStateName(String addressStateName) {
    AddressStateName = addressStateName;
  }

  public String getPhoneNumber() {
    return PhoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    PhoneNumber = phoneNumber;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        HrmPatient that = (HrmPatient) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
