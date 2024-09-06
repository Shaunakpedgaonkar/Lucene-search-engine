This project demonstrates the implementation of a full-featured text search engine using Apache Lucene. The goal is to create an efficient search engine capable of processing, indexing, and retrieving documents from the Cranfield Collection—a set of 1400 short abstracts. The project also involves the implementation of different scoring models such as the Vector Space Model and BM25, and evaluating the search engine's performance using TREC Eval for relevance judgements.
The project uses the Cranfield Collection, a set of 1400 short abstracts, as the document corpus. The search engine is built to handle the following tasks:

Document Indexing: Parsing the Cranfield Collection and indexing documents to allow for fast retrieval.
Query Processing: Accepting search queries and processing them using selected Lucene analyzers.
Scoring and Ranking: Implementing the BM25 and Vector Space Model to rank the documents based on their relevance to the queries.
Evaluation: Testing the search engine’s performance using 225 queries and relevance judgements from the Cranfield Collection. Mean Average Precision and Recall are calculated to assess the effectiveness of the search engine.

Pre-requisites
Java: Ensure that Java is installed (JDK 8 or higher).
Maven: Install Maven for managing dependencies.
Apache Lucene: The core library used to implement the search engine.

Search and Scoring Models
The search engine retrieves documents based on queries and scores them using two models:

BM25 Scoring Model: Implements a probabilistic approach to ranking documents.
Vector Space Model: Uses the term frequency-inverse document frequency (TF-IDF) method for ranking.
Evaluation
The TREC Eval tool is used to evaluate the performance of the search engine.
Relevance judgements are provided as part of the Cranfield Collection (cranqrel), and the performance is assessed using Mean Average Precision (MAP) and Recall.
