package com.rianwnoviantoro.jwt.domains.entities

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonView
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "brands")
class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var id: UUID? = null

    @Column
    var cd_brand: String? = null

    @Column(unique = true)
    var desc_brand: String? = null
}