/*
* Copyright 2015 herd contributors
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.finra.dm.app.security;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import org.finra.dm.model.dto.ApplicationUser;

@Component
public class DmUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken>
{
    private static final Logger LOGGER = Logger.getLogger(DmUserDetailsService.class);

    @Autowired
    private SecurityHelper securityHelper;

    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException
    {
        ApplicationUser user = (ApplicationUser) token.getPrincipal();

        Set<GrantedAuthority> authorities = securityHelper.mapRolesToFunctions(user.getRoles());
        SecurityUserWrapper result = new SecurityUserWrapper(user.getUserId(), "N/A", true, true, true, true, authorities, user);

        LOGGER.debug("Loaded User: " + result);
        return result;
    }
}
