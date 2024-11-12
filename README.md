# ai-data-crawler
Service to fetch the data from provided URL &amp; parse it into vector database for further AI-based processing

## To run the project, you need:
1. Run `docker compose up -d` - the command will run Milvus vector database
2. In your IntellijIDEA, gow to the upper-right corner, select `AiDataCrawlerApplication`, click on Configuration -> Edit
3. Paste the following parameters in environment variables (can modify based upon your needs) - `MILVUS_COLLECTION_NAME=vector_store;MILVUS_DBNAME=default;MILVUS_EMBEDDING_DIMENSION=384;MILVUS_HOST=localhost;MILVUS_PASSWORD=milvus;MILVUS_PORT=19530;MILVUS_USERNAME=root;SERVER_PORT=8080`
