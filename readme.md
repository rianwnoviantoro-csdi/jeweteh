# API DOC

### Sign up
> [POST] {url}/api/v1/signup

- Request:
```json
{
  "name": "john",
  "email": "john@mail.com",
  "password": "password"
}
```

- Response:
```json
{
  "out_stat": "T",
  "out_mess": "Signed up.",
  "out_data": null
}
```


### Sign in
> [POST] {url}/api/v1/signin

- Request:
```json
{
  "email": "john@mail.com",
  "password": "password"
}
```

- Response:
```json
{
  "out_stat": "T",
  "out_mess": "Signed in.",
  "out_data": {
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwMDAiLCJzdWIiOiJlMmUyNzFjNS03NzEzLTQwNjEtOGIxNi1lZjM3ZjFkZTQwYzciLCJpYXQiOjE2Nzg0MzI0OTcsImV4cCI6MTY3ODUxODg5N30.KJWPHsnmBBKPGveiFpgyrtpXb7tyAFn-0fxGpd5Td4c"
  }
}
```

- Cookies:
> token = eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwMDAiLCJzdWIiOiJlMmUyNzFjNS03NzEzLTQwNjEtOGIxNi1lZjM3ZjFkZTQwYzciLCJpYXQiOjE2Nzg0MzI0OTcsImV4cCI6MTY3ODUxODg5N30.KJWPHsnmBBKPGveiFpgyrtpXb7tyAFn-0fxGpd5Td4c

### Profile
> [GET] {url}/api/v1/me

- Response:
```json
{
  "out_stat": "T",
  "out_mess": "Success.",
  "out_data": {
    "id": "e2e271c5-7713-4061-8b16-ef37f1de40c7",
    "name": "john",
    "email": "john@mail.com",
    "createdAt": 1678421606745,
    "updatedAt": 1678421606745
  }
}
```

### Sign out
> [POST] {url}/api/v1/signout


- Response:
```json
{
  "out_stat": "T",
  "out_mess": "Signed out.",
  "out_data": null
}
```

- Cookies:
> token = ""
