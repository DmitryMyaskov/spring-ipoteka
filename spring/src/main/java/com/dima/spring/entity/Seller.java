package com.dima.spring.entity;

import com.dima.spring.validation.ItnCheck;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="seller")
@Validated
@ItnCheck(itnN = "ITN",value = "FACE_ITEM")
public class Seller implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Long Id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="User_Id")
    @Valid
    private User UserId;

    @Column(name="Face_Item")
    @NotNull
    @Enumerated(EnumType.STRING)
    private FaceItem FaceItem;

    @Column(name="ITN")
    private Long ITN;

    public Seller(){}

    public Seller(FaceItem face_item, long itn) {
        FaceItem = face_item;
        ITN = itn;
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public User getUserId() {
        return UserId;
    }

    public void setUserId(User userId) {
        UserId = userId;
    }

    public FaceItem getFaceItem() {
        return FaceItem;
    }

    public void setFaceItem(FaceItem faceItem) {
        FaceItem = faceItem;
    }

    public Long getITN() {
        return ITN;
    }

    public void setITN(Long ITN) {
        this.ITN = ITN;
    }
}