CREATE TABLE IF NOT EXISTS DownloadFile
(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    tag VARCHAR,
    url VARCHAR,
    basePath VARCHAR,
    name VARCHAR,
    absolutePath VARCHAR,
    totalSize INTEGER,
    downloading SMALLINT,
    showName VARCHAR,
    createTime VARCHAR,
    dtype VARCHAR,
    finished SMALLINT,
    fileType VARCHAR,
    finishTime VARCHAR,
    packageName VARCHAR
)
