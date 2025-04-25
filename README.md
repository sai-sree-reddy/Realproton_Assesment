# Realproton_Assesment

## Overview

This project demonstrates a complete DevOps pipeline for a simple containerized web application. It utilizes Docker for containerization, Prometheus for metrics scraping, Grafana for visualization, and GitHub Actions for continuous integration. The application exposes basic HTTP endpoints and Prometheus-compatible metrics, enabling real-time monitoring and observability. All services are configured to run locally using Docker Compose.

## Prerequisites

* **Docker:** Ensure Docker is installed on your system. You can find installation instructions [here](https://docs.docker.com/engine/install/).
* **Docker Compose:** Docker Compose is typically installed with Docker Desktop. If you need to install it separately, follow the instructions [here](https://docs.docker.com/compose/install/).
* **Git:** Git is required to clone the repository. You can download it from [here](https://git-scm.com/downloads).
* **A web browser:** To access the web application, Prometheus, and Grafana.
* **(Optional) Postman or a similar HTTP client:** To test the web application endpoints.

## Getting Started

Follow these steps to build and run the project locally:

1.  **Clone the repository:**
    ```bash
    git clone git@github.com:sai-sree-reddy/Realproton_Assesment.git
    cd Realproton_Assesment
    ```

2.  **Build and run the Docker containers:**
    ```bash
    docker-compose up --build -d
    ```
    This command will:
    * Build the Docker image for the web application if it doesn't exist.
    * Create and start the containers for the web application, Prometheus, and Grafana in detached mode.

## Accessing the Services

Once the containers are running, you can access the services through your web browser:

* **Web Application:** Navigate to `http://localhost:8080/`. You should see the message "App is running."
* **Prometheus:** Navigate to `http://localhost:9090/`. This will open the Prometheus web UI, where you can query metrics and see the targets being scraped.
* **Grafana:** Navigate to `http://localhost:3000/`. The default credentials are `admin` for both username and password. You will be prompted to change the password upon the first login.

## Grafana Dashboard

After logging into Grafana, you need to configure Prometheus as a data source and then import the dashboard to visualize the metrics.

### Configuring Prometheus as a Data Source

1.  On the Grafana home page, click on the **gear icon (Configuration)** in the left sidebar.
2.  Select **Data sources**.
3.  Click on **Add data source**.
4.  Search for and select **Prometheus**.
5.  In the **HTTP** section, set the **URL** to `http://prometheus:9090`. (Note: `prometheus` is the service name defined in the `docker-compose.yml` and allows Grafana to communicate with the Prometheus container).
6.  Click on **Save & test**. You should see a "Data source is working" message.

### Importing the Dashboard

1.  Click on the **plus icon (+)** in the left sidebar.

    **To create a new dashboard manually (example):**
    1.  Click on **Create** in the left sidebar and select **Dashboard**.
    2.  Click on **Add new panel**.
    3.  In the query editor, select the Prometheus data source you just configured.
    4.  Enter Prometheus queries to visualize the desired metrics (e.g., `http_server_requests_seconds_count`, `up`).
    5.  Choose the appropriate visualization type (e.g., Time series for request count, Gauge for uptime).
    6.  Customize the panel title and other options as needed.
    7.  Click **Apply**.

    **Example Visualizations:**

    * **Total Request Count:** Use a "Time series" visualization with the query `sum(http_server_requests_seconds_count)`.
    * **Application Uptime:** Use a "Gauge" visualization with the query `up{job="spring-boot-app"}` (adjust the `job` label if necessary).

## Testing the Web Application

You can test the web application endpoints using your browser or an HTTP client like Postman:

* **`/` Endpoint:** Send a GET request to `http://localhost:8080/`. The expected response is:
    ```
    App is running.
    ```

## GitHub Actions Pipeline

The project includes a GitHub Actions workflow (typically located in `.github/workflows/`) that automates the build and potentially test process upon code changes (e.g., push, pull request).

* **Workflow Trigger:** The workflow is configured to run on `push` and `pull_request` events on the main branch.

## Cleaning Up

To stop and remove the Docker containers, run the following command:

```bash
docker-compose down
