# Data Schema and Request for ReViewX

## For offline usage
- Using Realm db for collected NO-SQL data object
***

## For online usage
- Connect to server at `http://toBeDefine`
***

## For server
- To be found at [ReViewXServer](https://github.com/chutipon29301/ReViewXServer)
***

### General Structure
We will be using JSON objects in communication between Server and Client.

All of field name are written in camel case.

In Java, all recieved data should have their own classes, written in `POJO`, store in package `dao`

In this documentation all object will be write with `JSON` for east of understan
***

### Authentication
We will use `facebook` to authenticate all of our user
#### Description
    To be discuss

### Data Schema
For all object, they will have their own primary key `_id` except for object inside object

e.g.
```javascript
a = {
    '_id': 'fm834whtnfv9awhgj9aw4g',
    ...
}
```

#### Review Object
All review object will contain data for show in `Explore` page

```javascript
review = {
    '_id': '23awhjfg804awhjyg94w0uity432wg',
    'title': 'Star Wars',
    'theme': 'sci-fi',
    //... to be discuss
}
```

#### Review Detail Object
All detail about the review object
```javascript
reviewDetail = {
    '_id': '24wurmq49-wirq-wrvc-q,wr3',
    'reviewID': '23awhjfg804awhjyg94w0uity432wg',
    'detail': 'Detail about movie review',
    //...to be discuss
}
```

#### Configuration
Object which collect information about configuration of the app
```javascript
config = {
    '_id': 'config',
    'theme': 'dark',
    //...to be discuss
}
```

#### User Preference
Collection that collect all user information
```javascript
userPref = {
    '_id': 'userPrefs',
    'likeTheme': [
        'fantasy',
        'comady'
    ],
    'dislikeThene': [
        'sci-fi',
        'drama'
    ]
}
```