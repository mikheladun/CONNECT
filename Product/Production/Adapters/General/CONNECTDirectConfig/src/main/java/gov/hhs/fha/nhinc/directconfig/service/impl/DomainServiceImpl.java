/*
 * Copyright (c) 2009-2014, United States Government, as represented by the Secretary of Health and Human Services.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above
 *       copyright notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the documentation
 *       and/or other materials provided with the distribution.
 *     * Neither the name of the United States Government nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE UNITED STATES GOVERNMENT BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
/*
 Copyright (c) 2010, NHIN Direct Project
 All rights reserved.

 Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.

 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer
 in the documentation and/or other materials provided with the distribution.
 3. Neither the name of the The NHIN Direct Project (nhindirect.org) nor the names of its contributors may be used to endorse or promote
 products derived from this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS
 BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE
 GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 THE POSSIBILITY OF SUCH DAMAGE.
 */

package gov.hhs.fha.nhinc.directconfig.service.impl;

import gov.hhs.fha.nhinc.directconfig.dao.DomainDao;
import gov.hhs.fha.nhinc.directconfig.entity.Domain;
import gov.hhs.fha.nhinc.directconfig.entity.helpers.EntityStatus;
import gov.hhs.fha.nhinc.directconfig.service.ConfigurationServiceException;
import gov.hhs.fha.nhinc.directconfig.service.DomainService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.jws.WebService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * Service class for methods related to a Domain object.
 */
@Service("domainSvc")
@WebService(endpointInterface = "gov.hhs.fha.nhinc.directconfig.service.DomainService", portName = "ConfigurationServiceImplPort", targetNamespace = "http://nhind.org/config")
public class DomainServiceImpl extends SpringBeanAutowiringSupport implements DomainService {

    private static final Log log = LogFactory.getLog(DomainServiceImpl.class);

    @Autowired
    private DomainDao dao;

    /**
     * Initialization method.
     */
    @PostConstruct
    public void init() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        log.info("DomainService initialized");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addDomain(Domain domain) throws ConfigurationServiceException {
        dao.add(domain);
        log.info("Added Domain: " + domain.getDomainName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateDomain(Domain domain) throws ConfigurationServiceException {
        if (domain != null) {
            dao.update(domain);
            log.info("Modified Domain: " + domain.getDomainName());
        } else {
            log.debug("No domain provided");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDomainCount() throws ConfigurationServiceException {
        return dao.count();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Domain> getDomains(Collection<String> domainNames, EntityStatus status)
            throws ConfigurationServiceException {

        return dao.getDomains(new ArrayList<String>(domainNames), status);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeDomain(String domainName) throws ConfigurationServiceException {
        dao.delete(domainName);
        log.info("Modified Domain: " + domainName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeDomainById(Long domainId) throws ConfigurationServiceException {
        dao.delete(domainId);
        log.info("Modified Domain: " + domainId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Domain> listDomains(String lastDomainName, int maxResults) throws ConfigurationServiceException {
        List<Domain> result = dao.listDomains(lastDomainName, maxResults);

        if (result != null) {
            log.debug("Exit: " + result.toString());
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Domain> searchDomain(String domain, EntityStatus status) {
        List<Domain> result = dao.searchDomain(domain, status);

        if (result != null) {
            log.debug("Exit: " + result.toString());
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Domain getDomain(Long id) {
        Domain result = dao.getDomain(id);

        if (result != null) {
            log.debug("Exit: " + result.toString());
        }

        return result;
    }
}
