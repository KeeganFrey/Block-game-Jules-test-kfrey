"""
This module defines classes for dynamic game blocks, particularly those
with textures that change based on their neighbors.

The `current_texture` attribute in block instances (e.g., ConnectedTextureBlock)
stores a string that represents the name of the texture file to be used for
rendering the block (e.g., "stone_single.png", "wood_middle.png").

In a real game engine or graphics system:
1. These texture names would correspond to actual image files (e.g., .png, .jpg)
   stored in the game's assets directory.
2. A Texture Management system (Asset Manager or similar) would be responsible
   for loading these image files from disk into memory (e.g., onto the GPU
   as texture objects) at an appropriate time (e.g., game startup, level load).
3. When a block needs to be rendered, the game's rendering engine would use the
   `current_texture` string (e.g., "stone_single.png") as a key or identifier
   to look up the actual loaded texture data (the image object) from the
   Texture Manager. This loaded texture would then be applied to the block's
   3D model during the rendering process.

The logic in this file focuses on determining the correct texture *name* based
on game state (like neighboring blocks), and it assumes a separate system
handles the loading and application of these textures.
"""

# Conceptual Texture Manager - In a real game, this would handle loading images.
class TextureManager:
    def __init__(self):
        self.loaded_textures = {} # Maps texture_name (str) to actual texture data (e.g., an image object)

    def load_texture(self, texture_name):
        # In a real implementation, this would load the image file from disk
        # and create a GPU texture object or similar.
        # For now, it just simulates loading by storing the name and a placeholder.
        if texture_name not in self.loaded_textures:
            print(f"[TextureManager] 'Loading' {texture_name}...")
            self.loaded_textures[texture_name] = f"mock_texture_data_for_{texture_name}"
        return self.loaded_textures[texture_name]

    def get_texture(self, texture_name):
        if texture_name not in self.loaded_textures:
            # In a real game, attempting to get a non-loaded texture might
            # trigger a load, return a default/error texture, or raise an error.
            print(f"[TextureManager] Warning: Texture '{texture_name}' not pre-loaded. Attempting to load.")
            return self.load_texture(texture_name)
        return self.loaded_textures[texture_name]

# Example usage (conceptual, not directly used by Block classes below):
# texture_manager = TextureManager()
# example_texture = texture_manager.load_texture("grass_single.png")
# print(f"Example loaded texture: {texture_manager.get_texture('grass_single.png')}")


class Block:
    def __init__(self, position, block_type):
        self.position = position
        self.block_type = block_type

class ConnectedTextureBlock(Block):
    def __init__(self, position, block_type):
        super().__init__(position, block_type)
        self.current_texture = f"{self.block_type}_single.png"

    # Ideal call times for this method:
    # - After this block is added to the world.
    # - When an adjacent block is added or removed (notifying this block).
    def update_texture(self, world_data):
        my_type = self.block_type

        if not world_data or self.position not in world_data:
            self.current_texture = f"{my_type}_single.png"
            return

        neighbors_data = self.get_neighbors(world_data)
        
        east_type = neighbors_data.get("east_block_type")
        west_type = neighbors_data.get("west_block_type")

        if east_type == my_type and west_type == my_type:
            self.current_texture = f"{my_type}_middle.png"
        elif west_type == my_type: # and east_type != my_type (implicit)
            self.current_texture = f"{my_type}_left_end.png"
        elif east_type == my_type: # and west_type != my_type (implicit)
            self.current_texture = f"{my_type}_right_end.png"
        else: # neither east nor west is my_type
            self.current_texture = f"{my_type}_single.png"

    def get_neighbors(self, world_data):
        relative_positions = {
            "north": (0, 0, 1),
            "south": (0, 0, -1),
            "east": (1, 0, 0),
            "west": (-1, 0, 0),
            "up": (0, 1, 0),
            "down": (0, -1, 0),
        }

        neighbors_data = {}
        # Assuming self.position is a tuple like (x, y, z)
        x, y, z = self.position

        for direction, rel_pos in relative_positions.items():
            neighbor_pos = (x + rel_pos[0], y + rel_pos[1], z + rel_pos[2])
            
            # world_data maps positions to block instances
            neighbor_block = world_data.get(neighbor_pos)
            if neighbor_block and hasattr(neighbor_block, 'block_type'):
                neighbors_data[f"{direction}_block_type"] = neighbor_block.block_type
            else:
                neighbors_data[f"{direction}_block_type"] = None
        
        return neighbors_data

# This function is a simplified representation of how a game engine's rendering loop
# would use the block's current_texture attribute and a texture manager.
def render_block_in_world(block_instance, texture_manager):
    """
    Illustrative function to simulate rendering a block.
    In a real game, this would involve complex graphics operations.
    """
    texture_name = block_instance.current_texture
    texture_data = texture_manager.get_texture(texture_name) # Assumes texture_manager is an instance of TextureManager
    print(f"Rendering block {block_instance.block_type} at {block_instance.position} using texture '{texture_name}' (Data: {texture_data})")

def notify_block_update(block_instance, world_data):
    """
    Updates the texture of the given block and its relevant neighbors.
    Assumes world_data maps positions to block instances.
    """
    block_instance.update_texture(world_data)

    relative_neighbor_positions = [
        (0, 0, 1), (0, 0, -1),  # North, South
        (1, 0, 0), (-1, 0, 0), # East, West
        (0, 1, 0), (0, -1, 0)   # Up, Down
    ]

    x, y, z = block_instance.position

    for rel_pos in relative_neighbor_positions:
        neighbor_pos = (x + rel_pos[0], y + rel_pos[1], z + rel_pos[2])

        if neighbor_pos in world_data:
            neighbor_block = world_data[neighbor_pos]
            if isinstance(neighbor_block, ConnectedTextureBlock):
                neighbor_block.update_texture(world_data)

if __name__ == "__main__":
    # 1. Create an instance of TextureManager
    texture_manager = TextureManager()

    # 2. Initialize world_data
    world_data = {} # Maps (x,y,z) tuples to block instances

    # 3. Define a block type
    MY_DYNAMIC_BLOCK_TYPE = "stone"

    # 4. Create ConnectedTextureBlock instances
    block1 = ConnectedTextureBlock(position=(0,0,0), block_type=MY_DYNAMIC_BLOCK_TYPE)
    block2 = ConnectedTextureBlock(position=(1,0,0), block_type=MY_DYNAMIC_BLOCK_TYPE)  # East of block1
    block3 = ConnectedTextureBlock(position=(-1,0,0), block_type=MY_DYNAMIC_BLOCK_TYPE) # West of block1
    block4 = ConnectedTextureBlock(position=(0,1,0), block_type="wood") # A different type, above block1
    
    # For demonstration, let's also add a non-connected block to ensure it's handled gracefully
    # non_connected_block = Block(position=(0,0,1), block_type="iron")


    # 5. Add blocks to world_data
    world_data[block1.position] = block1
    world_data[block2.position] = block2
    world_data[block3.position] = block3
    world_data[block4.position] = block4
    # world_data[non_connected_block.position] = non_connected_block

    # 6. Initial update for all connected texture blocks
    # This simulates placing blocks and having them (and their neighbors) update.
    # Using notify_block_update for each block after initial setup ensures all are correctly set.
    # Alternatively, a simpler loop of block.update_texture(world_data) for all CTBs can work too.
    
    # Initial full update pass
    print("--- Initializing block textures (first pass based on current world state) ---")
    for pos, block in world_data.items():
        if isinstance(block, ConnectedTextureBlock):
            block.update_texture(world_data) # Initial update based on neighbors

    # To ensure connected textures are fully resolved, especially if notify_block_update
    # was more sophisticated or if there were more complex dependencies,
    # sometimes a second pass or a more targeted update (like calling notify_block_update
    # for each block added) would be used. For this example, the above is sufficient
    # to show the basic principle.

    # 7. Print the initial state of the dynamic blocks
    print("\n--- Initial Block States ---")
    # Sort by position for consistent output, optional
    sorted_block_positions = sorted(world_data.keys())
    for pos in sorted_block_positions:
        block = world_data[pos]
        # We only care about rendering our dynamic blocks of MY_DYNAMIC_BLOCK_TYPE for this check
        if isinstance(block, ConnectedTextureBlock) and block.block_type == MY_DYNAMIC_BLOCK_TYPE:
            render_block_in_world(block, texture_manager)
        elif isinstance(block, Block): # Render other simple blocks too for completeness
            print(f"Basic block {block.block_type} at {block.position} - Texture: N/A (not a ConnectedTextureBlock or not of target type)")


    # 8. Simulate removing a block and see how it affects others
    removed_block_pos = (1,0,0) # Position of block2
    
    print(f"\n--- Removing block at {removed_block_pos} ({world_data.get(removed_block_pos, Block(None,'N/A')).block_type}) ---")
    if removed_block_pos in world_data:
        removed_block_instance = world_data.pop(removed_block_pos) # Use pop to get the instance if needed
        
        # Notify neighbors of the removed block that they might need to update.
        # The most straightforward way is to call notify_block_update on the neighbors.
        # For this example, we'll find blocks adjacent to the removed block and update them.
        # More simply, as per prompt, re-update all remaining.
        
        print(f"Block {removed_block_instance.block_type} at {removed_block_instance.position} removed.")
    else:
        print(f"No block found at {removed_block_pos} to remove.")

    # Re-update all remaining ConnectedTextureBlocks as their neighborhood might have changed.
    # A more optimized approach in a real game would be to only update the direct neighbors
    # of the removed block by calling notify_block_update on them.
    print("\n--- Updating remaining blocks after removal ---")
    for pos, block in world_data.items():
        if isinstance(block, ConnectedTextureBlock):
            # Calling notify_block_update ensures that not only the block itself
            # but also its neighbors are correctly updated if they are ConnectedTextureBlocks.
            # However, the prompt suggests a simpler block.update_texture(world_data) for this step.
            block.update_texture(world_data)


    print("\n--- Block States After Removal ---")
    sorted_block_positions_after_removal = sorted(world_data.keys())
    for pos in sorted_block_positions_after_removal:
        block = world_data[pos]
        if isinstance(block, ConnectedTextureBlock) and block.block_type == MY_DYNAMIC_BLOCK_TYPE:
            render_block_in_world(block, texture_manager)
        elif isinstance(block, Block):
             print(f"Basic block {block.block_type} at {block.position} - Texture: N/A (not a ConnectedTextureBlock or not of target type)")
