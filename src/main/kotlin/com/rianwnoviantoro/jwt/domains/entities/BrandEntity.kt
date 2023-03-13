package com.rianwnoviantoro.jwt.domains.entities

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "brands")
class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null

    @Column
    var cd_brand: String? = null

    @Column(unique = true)
    var desc_brand: String? = null
}