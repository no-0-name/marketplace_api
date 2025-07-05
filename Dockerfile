FROM ubuntu:latest
LABEL authors="gorde"

ENTRYPOINT ["top", "-b"]