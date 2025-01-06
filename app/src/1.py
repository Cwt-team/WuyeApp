import os

def list_all_files(root_dir):
    """
    遍历指定目录及其子目录下的所有文件，并返回文件名的列表。

    Args:
        root_dir: 要遍历的根目录路径。

    Returns:
        一个包含所有文件名的列表。
    """
    all_files = []
    for dirpath, dirnames, filenames in os.walk(root_dir):
        for filename in filenames:
            all_files.append(os.path.join(dirpath, filename))  # 可以选择只添加 filename
    return all_files

if __name__ == "__main__":
    current_directory = os.getcwd()  # 获取当前项目目录
    file_names = list_all_files(current_directory)

    print("当前项目的所有文件（包含路径）：")
    for file_path in file_names:
        print(file_path)

    # 如果只需要文件名，可以这样做：
    print("\n当前项目的所有文件名（不包含路径）：")
    for file_path in file_names:
        print(os.path.basename(file_path))
