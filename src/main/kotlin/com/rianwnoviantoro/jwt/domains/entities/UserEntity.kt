package com.rianwnoviantoro.jwt.domains.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.Date
import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "users")
class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null

    @Column
    var name: String? = null

    @Column(unique = true)
    var email: String? = null

    @Column
    var password: String? = null
        @JsonIgnore
        set(value) {
            val passwordEncoder = BCryptPasswordEncoder()
            field = passwordEncoder.encode(value)
        }

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(nullable = false)
    var createdAt: Date? = null

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column
    var updatedAt: Date? = null
}