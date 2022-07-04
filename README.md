# SOAP web services using Spring Boot

## API documentation
### /getUserByLogin
Request:
```xml  
<xs:element name="getUserByLoginRequest">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="login" type="xs:string" />
            </xs:sequence>
        </xs:complexType>
    </xs:element>
```   
Response:
```xml  
<xs:element name="getUserByLoginResponse">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="login" type="xs:string" />
                <xs:element name="name" type="xs:string" />
                <xs:element name="password" type="xs:string" />
                <xs:element name="roles" type="xs:string" maxOccurs="unbounded"/>
            </xs:sequence>
        </xs:complexType>
    </xs:element>
```   
### /getAllUsers
Request:
```xml

<xs:element name="getAllUsersRequest">
  <xs:complexType>

  </xs:complexType>
</xs:element>
```   
Response:
```xml  
<xs:element name="getAllUsersResponse">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="users" type="xs:string" maxOccurs="unbounded" />
            </xs:sequence>
        </xs:complexType>
    </xs:element>
```  

### /addUser
Request:
```xml  
<xs:element name="addUserRequest">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="users" type="tns:users" />
            </xs:sequence>
        </xs:complexType>
    </xs:element>
```  
Response:
```xml  
<xs:element name="addUserResponse">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="users" type="tns:users" />
                <xs:element name="message" type="xs:string" />
            </xs:sequence>
        </xs:complexType>
    </xs:element>
```  

### /updateUserByLogin
Request:
```xml  
<xs:element name="updateUserByLoginRequest">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="users" type="tns:users" />
            </xs:sequence>
        </xs:complexType>
    </xs:element>
```  
Response:
```xml  
<xs:element name="updateUserByLoginResponse">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="users" type="tns:users" />
                <xs:element name="message" type="xs:string" />
            </xs:sequence>
        </xs:complexType>
    </xs:element>
```  
### /deleteUserByLogin
Request:
```xml  
<xs:element name="deleteUserByLoginRequest">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="login" type="xs:string" />
            </xs:sequence>
        </xs:complexType>
    </xs:element>
```  
Response:
```xml  
<xs:element name="deleteUserByLoginResponse">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="message" type="xs:string" />
            </xs:sequence>
        </xs:complexType>
    </xs:element>
```  
### Database diagrams
<img src="https://i.ibb.co/fQDRPBY/2022-07-04-01-18-34.png" alt="soap-service_db_diagrams">
