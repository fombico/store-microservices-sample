## Other

- Generate an RSA key
    ```
    openssl genrsa -out rsa.pem 2048
    ```
- Use `cat` to display it:
    ```
    cat rsa.pem
    
    -----BEGIN RSA PRIVATE KEY-----
    ...
    -----END RSA PRIVATE KEY-----
    ```
- display the public key:
    ```
    openssl rsa -in rsa.pem -pubout
    
    -----BEGIN PUBLIC KEY-----
    ...
    -----END PUBLIC KEY-----
    ```