# profiles_authorizer
Profiles Authorizer uses User Profile UUID (GUID) to validate:
- if authorization token is present (as: if user has been authenticated before)
- if token is still valid (as: not expired) 
- which part of the system this token allows user to access (as: authorization per individual services)
