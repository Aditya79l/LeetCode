import os
import json
import requests

SESSION = os.environ["LEETCODE_SESSION"]
CSRF = os.environ["CSRFTOKEN"]

HEADERS = {
    "Content-Type": "application/json",
    "Cookie": f"LEETCODE_SESSION={SESSION}; csrftoken={CSRF}",
    "x-csrftoken": CSRF,
    "Referer": "https://leetcode.com",
    "User-Agent": "Mozilla/5.0"
}

GRAPHQL_URL = "https://leetcode.com/graphql"


def get_all_submissions():
    """Fetch all accepted submissions (paginated)."""
    submissions = []
    offset = 0
    limit = 20

    while True:
        query = {
            "query": """
            query submissionList($offset: Int!, $limit: Int!) {
              submissionList(offset: $offset, limit: $limit) {
                lastKey
                hasNext
                submissions {
                  id
                  title
                  titleSlug
                  statusDisplay
                  lang
                  timestamp
                }
              }
            }
            """,
            "variables": {"offset": offset, "limit": limit}
        }

        resp = requests.post(GRAPHQL_URL, json=query, headers=HEADERS)
        data = resp.json()["data"]["submissionList"]

        for sub in data["submissions"]:
            if sub["statusDisplay"] == "Accepted":
                submissions.append(sub)

        if not data["hasNext"]:
            break
        offset += limit

    return submissions


def get_submission_code(submission_id):
    """Fetch the actual code for a submission."""
    query = {
        "query": """
        query submissionDetails($submissionId: Int!) {
          submissionDetails(submissionId: $submissionId) {
            code
            lang { name verboseName }
          }
        }
        """,
        "variables": {"submissionId": int(submission_id)}
    }
    resp = requests.post(GRAPHQL_URL, json=query, headers=HEADERS)
    return resp.json()["data"]["submissionDetails"]


def get_problem_details(slug):
    """Fetch problem title, difficulty, URL, and description."""
    query = {
        "query": """
        query questionData($titleSlug: String!) {
          question(titleSlug: $titleSlug) {
            questionFrontendId
            title
            difficulty
            content
          }
        }
        """,
        "variables": {"titleSlug": slug}
    }
    resp = requests.post(GRAPHQL_URL, json=query, headers=HEADERS)
    return resp.json()["data"]["question"]


LANG_EXT = {
    "python3": "py", "python": "py", "java": "java",
    "cpp": "cpp", "c": "c", "javascript": "js",
    "typescript": "ts", "go": "go", "rust": "rs",
    "kotlin": "kt", "swift": "swift", "ruby": "rb",
    "scala": "scala", "csharp": "cs"
}

seen_slugs = set()

submissions = get_all_submissions()
print(f"Found {len(submissions)} accepted submissions")

for sub in submissions:
    slug = sub["titleSlug"]

    if slug in seen_slugs:
        continue
    seen_slugs.add(slug)

    print(f"Processing: {sub['title']}")

    try:
        details = get_problem_details(slug)
        code_data = get_submission_code(sub["id"])

        if not code_data:
            print(f"  Skipping {slug} — could not fetch code")
            continue

        problem_id = details["questionFrontendId"].zfill(4)
        title = details["title"]
        difficulty = details["difficulty"]
        lang = code_data["lang"]["name"]
        code = code_data["code"]
        ext = LANG_EXT.get(lang, "txt")
        url = f"https://leetcode.com/problems/{slug}/"

        folder = f"{problem_id}-{slug}"
        os.makedirs(folder, exist_ok=True)

        readme_path = os.path.join(folder, "README.md")
        if not os.path.exists(readme_path):
            readme = f"""# {problem_id}. {title}

**Difficulty:** {difficulty}  
**LeetCode URL:** [{url}]({url})

## Problem

{details.get('content', '_Description not available_')}
"""
            with open(readme_path, "w", encoding="utf-8") as f:
                f.write(readme)

        solution_path = os.path.join(folder, f"solution.{ext}")
        if not os.path.exists(solution_path):
            with open(solution_path, "w", encoding="utf-8") as f:
                f.write(code)

        print(f"  Saved: {folder}/")

    except Exception as e:
        print(f"  Error on {slug}: {e}")

print("Sync complete.")
