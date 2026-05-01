package pe.order.service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_snapshot")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSnapshot {

    @Id
    private Long userId;

    private String name;
    private String email;
    private String phone;
    private Boolean enabled;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
    
    
}