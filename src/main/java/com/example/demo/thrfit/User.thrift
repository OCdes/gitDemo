

namespace java thrfit.generated

typedef i16 short
typedef i32 int

struct User {
    1: i32 id
    2: string name
}

service UserService {
    User getUser(1:i32 uid)
}