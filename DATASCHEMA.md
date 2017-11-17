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

### MovieSuggestion

#### MovieObject
Show movie detail in MovieSuggestion fragment and SearchMovie fragement
```javascript
movieObject = {
    '_id': 'dsfn9u43tyg8afw4ba4uageh',
    'movieName': 'Star Wars',
    'releaseDate': '14/12/2017',
    'genre': [
        'animation',
        'family',
        'fantasy'
    ],
    'score': 98,
    'pic': 'http://www.google.com'
}
```

### ReviewSuggestion

#### ReviewObject
Show review detail in ReviewSuggestion fragment
```javascript
movieObject = {
    '_id': 'dsfn9u43tyg8afw4ba4uageh',
    'movieName': 'Star Wars',
    'reviewer': 'Ming Yanisa',
    'threeWords': [
        'beautiful',
        'emotional',
        'terrible'
    ],
    'score': 98,
    'pic': 'http://www.google.com'
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
    ],
    'comment': 'Long time ago, in the galaxy far far away',
    'point': 143
}
```
