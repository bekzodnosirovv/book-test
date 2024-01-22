package book.store.config;


import book.store.dtos.CustomUserDto;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Getter
public class CustomUserDetails implements UserDetails {

     private CustomUserDto customUserDto;

     public CustomUserDetails(CustomUserDto customUserDto){
         this.customUserDto=customUserDto;
     }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new LinkedList<>();
        list.add(new SimpleGrantedAuthority(customUserDto.getRole()));
        return list;
    }

    @Override
    public String getPassword() {
        return customUserDto.getPassword();
    }

    @Override
    public String getUsername() {
        return customUserDto.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
